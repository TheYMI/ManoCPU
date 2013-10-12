package Emulator.Global;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * **********************
 * Created By: Yuval Tzur
 * Date: 07/06/13
 * Time: 17:32
 * Description: This is a class loader implementation that allows loading classes by providing a relative path to their compiled implementation.
 * ***********************
 */

public class Loader extends ClassLoader {

    String mTemplateName;

    Loader(String templateName){
        mTemplateName = templateName;
    }

    @Override
    public Class<?> loadClass(String s) {
        return findClass(s);
    }

    @Override
    public Class<?> findClass(String s) {
        try {
            byte[] bytes = loadClassData(s);
            return defineClass(s, bytes, 0, bytes.length);
        } catch (IOException ioe) {
            try {
                return super.loadClass(s);
            } catch (ClassNotFoundException ignore) {}
            ioe.printStackTrace(java.lang.System.out);
            return null;
        }
    }

    private byte[] loadClassData(String className) throws IOException {
        String baseName = className.replaceAll("\\.", "/");
        baseName = (new File(baseName)).getName();
        String currentDir = new File(".").getAbsolutePath();
        File f = new File(currentDir + "\\Machines\\" + mTemplateName + "\\" + baseName + ".class");
        int size = (int) f.length();
        byte buff[] = new byte[size];
        FileInputStream fis = new FileInputStream(f);
        DataInputStream dis = new DataInputStream(fis);
        dis.readFully(buff);
        dis.close();
        return buff;
    }
}
