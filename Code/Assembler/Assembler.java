package Assembler;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * **********************
 * Created By: Yuval Tzur
 * Date: 10/06/2013
 * Time: 19:46:31
 * Description: This class will assemble assembly code into binary code.
 *              This will create the file Program.dat, from which the emulator will load the program.
 * ***********************
 */

public class Assembler implements iAssembler {

	public Assembler() { /* Do nothing */ }

	public void assemble(){

		String  filename = "appData\\Program.dat";

		try {

			FileReader inputFile = new FileReader(filename);
			Lexer      lexer     = new Lexer(inputFile);
			parser     assembler = new parser(lexer);

			assembler.parse();

		} catch (FileNotFoundException fnf){
			JOptionPane.showMessageDialog(null, "ERROR: Could not open " + filename + " for reading!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e.getLocalizedMessage());
		} catch (Error e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e.getLocalizedMessage());

		}

	}

}