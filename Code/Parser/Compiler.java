package Parser;

import javax.swing.*;
import java.io.*;

/**
 * **********************
 * Created By: Yuval Tzur
 * Date: 18/01/13
 * Time: 16:09
 * Description: This class will compile an instruction set template into java code.
 *              This will create the class iInstructionsUCode.
 * ***********************
 */

public class Compiler {

    public void compile(String templateName){

        String filename   = "appData\\Template.dat";
        String currentDir = new File(".").getAbsolutePath();
        String[] command  = {
                "java",
                "-jar",
                currentDir + "\\Resources\\java-cup-11a.jar",
                "-parser",
                templateName + "Parser",
                "-destdir",
                currentDir + "\\Machines\\" + templateName,
                currentDir + "\\Machines\\"+ templateName + "\\Assembler.cup",
        };

        try {

            FileReader inputFile = new FileReader(filename);
            Lexer      lexer     = new Lexer(inputFile);
            parser     Parser    = new parser(lexer);

            Parser.set_mTemplateName(templateName);
            Parser.parse();
            Process p = Runtime.getRuntime().exec(command);

            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String line=reader.readLine();
            while(line!=null)
            {
                System.out.println(line);
                line = reader.readLine();
            }

            compileParser(currentDir, templateName);

            // Copy the flex file.
            String orig      = "Resources\\Lexer.class";
            String dest      = "Machines\\" + templateName + "\\Lexer.class";
            InputStream in   = new FileInputStream(orig);
            OutputStream out = new FileOutputStream(dest);
            byte[] buf       = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();

            // Remove all unnecessary files.
            File file = new File(currentDir + "\\Machines\\" + templateName + "\\Assembler.cup");
            if(file.delete()){
                System.out.println(file.getName() + " was deleted!");
            }else{
                System.out.println("Delete operation has failed.");
            }

            file = new File(currentDir + "\\Machines\\" + templateName + "\\sym.java");
            if(file.delete()){
                System.out.println(file.getName() + " was deleted!");
            }else{
                System.out.println("Delete operation has failed.");
            }

            file = new File(currentDir + "\\Machines\\" + templateName + "\\" + templateName + "Parser.java");
            if(file.delete()){
                System.out.println(file.getName() + " was deleted!");
            }else{
                System.out.println("Delete operation has failed.");
            }

            file = new File(currentDir + "\\Machines\\" + templateName + "\\" + templateName + "Assembler.java");
            if(file.delete()){
                System.out.println(file.getName() + " was deleted!");
            }else{
                System.out.println("Delete operation has failed.");
            }

            file = new File(currentDir + "\\Machines\\" + templateName + "\\" + templateName + "UCode.java");
            if(file.delete()){
                System.out.println(file.getName() + " was deleted!");
            }else{
                System.out.println("Delete operation has failed.");
            }

        } catch (FileNotFoundException fnf){
            JOptionPane.showMessageDialog(null, "ERROR: Could not open " + filename + " for reading!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getLocalizedMessage());
        } catch (Error e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getLocalizedMessage());
        }

    }

    static void compileParser(String currentDir, String templateName) throws Exception, Error{

        String[] command  = {
                "javac",
                "-classpath",
                currentDir + "\\Resources\\*",
                currentDir + "\\Machines\\" + templateName + "\\" + templateName + "Parser.java",
                currentDir + "\\Machines\\" + templateName + "\\" + templateName + "Assembler.java",
                currentDir + "\\Machines\\" + templateName + "\\" + templateName + "UCode.java"
        };

        Process p = Runtime.getRuntime().exec(command);

        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        String line=reader.readLine();
        while(line!=null)
        {
            System.out.println(line);
            line=reader.readLine();
        }

    }

}
