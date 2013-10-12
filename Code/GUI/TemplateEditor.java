package GUI;

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
 * Date: 07/06/13
 * Time: 23:24
 * Description:
 * ***********************
 */

public class TemplateEditor extends JDialog {

    JEditorPane editor;
    JLabel      isName;
    JTextPane   namePane;
    JButton     useButton;
    JButton     loadButton;
    JButton     saveButton;
    JButton     cancelButton;
    JPanel      templateEditor;

    int mWidth, mHeight;

    public TemplateEditor(final JPanel mainPanel) {

        mWidth = mainPanel.getWidth();
        mHeight = mainPanel.getHeight();

        setContentPane(templateEditor);
        setTitle("Template Editor");

        namePane.setText(mainWindow.systemName);

        setPreferredSize(new Dimension(mWidth, mHeight));
        setLocation(mainPanel.getWidth() + 20, 0);

        String filename = "appData\\Template.dat";

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
                JFileChooser fileChooser = new JFileChooser("Templates");
                int result = fileChooser.showDialog(templateEditor, "Load");

                if (result == JFileChooser.APPROVE_OPTION) {
                    try {
                        File file = fileChooser.getSelectedFile();
                        namePane.setText(file.getName().replace(".txt", ""));
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
                JFileChooser fileChooser = new JFileChooser("Templates");
                int result = fileChooser.showDialog(templateEditor, "Save");

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
                String filename = "appData\\Template.dat";

                try {
                    mainWindow.systemName = namePane.getText();
                    if (mainWindow.systemName.equals("")){
                        mainWindow.systemName = "Default";
                    }
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
