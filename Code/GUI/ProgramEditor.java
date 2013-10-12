package GUI;

import Assembler.iAssembler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * **********************
 * Created By: Yuval Tzur
 * Date: 02/03/13
 * Time: 19:48
 * Description: Program editor GUI.
 * ***********************
 */

public class ProgramEditor extends JDialog {

    JEditorPane editor;
    JButton     cancelButton;
    JButton     loadButton;
    JButton     saveButton;
    JButton     useButton;
    JPanel      programEditor;

    int        mWidth, mHeight;

    public ProgramEditor(final JPanel mainPanel) {

        mWidth = mainPanel.getWidth();
        mHeight = mainPanel.getHeight();

        setContentPane(programEditor);
        setTitle("Program Editor");

        setPreferredSize(new Dimension(mWidth / 2, mHeight));
        setLocation(mainPanel.getWidth() + 20, 0);

        String filename = "appData\\Program.dat";

        try {
            File file = new File(filename);
            editor.setPage(file.toURI().toURL());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        }

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                dispose();
            }
        });
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                JFileChooser fileChooser = new JFileChooser("Programs");
                int result = fileChooser.showDialog(programEditor, "Load");

                if (result == JFileChooser.APPROVE_OPTION) {
                    try {
                        File file = fileChooser.getSelectedFile();
                        editor.setPage(file.toURI().toURL());
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
                    }
                    editor.setPreferredSize(new Dimension(-1, mHeight));
                }
            }
        });
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                JFileChooser fileChooser = new JFileChooser("Programs");
                int result = fileChooser.showDialog(programEditor, "Save");

                if (result == JFileChooser.APPROVE_OPTION) {
                    try {
                        File file = fileChooser.getSelectedFile();
                        FileWriter outFile = new FileWriter(file);
                        BufferedWriter out = new BufferedWriter(outFile);
                        out.write(editor.getText());
                        out.close();
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
                    }
                }
            }
        });
        useButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String filename = "appData\\Program.dat";

                try {
                    FileWriter outFile = new FileWriter(filename);
                    BufferedWriter out = new BufferedWriter(outFile);
                    out.write(editor.getText());
                    out.close();
                    dispose();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
                } catch (Error e) {
                    JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
                }
            }
        });

        pack();
        setVisible(true);

    }

}
