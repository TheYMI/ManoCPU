package GUI;

import Emulator.Global.*;
import Parser.Compiler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.System;

/**
 * **********************
 * Created By: Yuval Tzur
 * Date: 23/02/13
 * Time: 03:21
 * Description: Main GUI window for the user.
 * ***********************
 */

public class mainWindow extends JDialog implements Constants {

    JPanel      mainPanel;
    JTextField  mainTitle;
    JScrollPane memPanel;
    JScrollPane varPanel;
    JCheckBox   uCode;
    JTable      memTable;
    JTable      varTable;
    JButton     exitButton;
    JButton     runButton;
    JButton     loadButton;
    JButton     instructionsButton;
    JButton     assembleButton;
    JButton     compileButton;
    JButton     newTemplateButton;
    JButton     defaultTemplateButton;
    JButton     nextButton;
    JButton     resetButton;
    JList       templateList;

    ProgramEditor  programEditor;
    TemplateEditor templateEditor;
    TimeOutDialog  timeOutDialog;

    JLabel     command;
    JTextField commandContent;
    JLabel     currentMachine;
    JTextField currentMachineContent;

    JLabel e;
    JTextField eContent;
    JLabel ac;
    JTextField acContent;
    JLabel ir;
    JTextField irContent;
    JLabel dr;
    JTextField drContent;
    JLabel pc;
    JTextField pcContent;
    JLabel i;
    JTextField iContent;
    JLabel ar;
    JTextField arContent;
    JLabel tr0;
    JTextField tr0Content;
    JLabel tr1;
    JTextField tr1Content;
    JLabel fgi;
    JTextField fgiContent;
    JLabel inpr;
    JTextField inprContent;
    JLabel fgo;
    JTextField fgoContent;
    JLabel outr;
    JTextField outrContent;
    JLabel ien;
    JTextField ienContent;
    JLabel r;
    JTextField rContent;

    static Emulator   mEmulator = new Emulator();
    static Compiler   mCompiler = new Compiler();

    public static String systemName;

    public mainWindow() {

        setContentPane(mainPanel);
        setTitle("Mano CPU Emulator");

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (programEditor != null) {
                    programEditor.dispose();
                }
                if (templateEditor != null) {
                    templateEditor.dispose();
                }
                dispose();
                System.exit(0);
            }
        });
        runButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int timeoutCounter = 0;
                while (mEmulator.run(false)) { // Run until the program ended.
                    if (++timeoutCounter == TIMEOUT){
                        timeOutDialog = new TimeOutDialog(mEmulator);
                        timeoutCounter = 0;
                    }
                }
                eContent.setText(mEmulator.getSystemComponent(E));
                acContent.setText(mEmulator.getSystemComponent(AC));
                irContent.setText(mEmulator.getSystemComponent(IR));
                drContent.setText(mEmulator.getSystemComponent(DR));
                pcContent.setText(mEmulator.getSystemComponent(PC));
                iContent.setText(mEmulator.getSystemComponent(I));
                arContent.setText(mEmulator.getSystemComponent(AR));
                tr0Content.setText(mEmulator.getSystemComponent(TR0));
                tr1Content.setText(mEmulator.getSystemComponent(TR1));
                fgiContent.setText(mEmulator.getSystemComponent(FGI));
                inprContent.setText(mEmulator.getSystemComponent(INPR));
                fgoContent.setText(mEmulator.getSystemComponent(FGO));
                outrContent.setText(mEmulator.getSystemComponent(OUTR));
                ienContent.setText(mEmulator.getSystemComponent(IEN));
                rContent.setText(mEmulator.getSystemComponent(R));
                setMemPanel(mEmulator.getSystemMemory());
            }
        });
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                programEditor = new ProgramEditor(mainPanel);
            }
        });
        instructionsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String filename = "appData\\Metadata.dat";

                systemName = templateList.getSelectedValue().toString();
                try {
                    mEmulator.loadTemplate(systemName);
                    currentMachineContent.setText(systemName);
                    FileWriter outFile = new FileWriter(filename);
                    BufferedWriter out = new BufferedWriter(outFile);
                    out.write(systemName);
                    out.close();
                } catch (Exception e){ /* Handled in loadTemplate */ } catch (Error e){ /* Handled in loadTemplate */ }
            }
        });
        assembleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                mEmulator.assemble();
                setVarPanel();
                resetButton.doClick();
            }
        });
        compileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                boolean success = (new File("Machines\\" + systemName)).mkdirs();
                if (!success) {
                    JOptionPane.showMessageDialog(null, "WARNING: Existing instruction set. Changes will take effect after the next system restart.");
                }

                String filename = "appData\\Metadata.dat";

                try {
                    mCompiler.compile(systemName);
                    mEmulator.loadTemplate(systemName);
                    FileWriter outFile = new FileWriter(filename);
                    BufferedWriter out = new BufferedWriter(outFile);
                    out.write(systemName);
                    out.close();
                    setTemplateList();
                } catch (Exception e){ /* Handled in loadTemplate */ } catch (Error e){ /* Handled in loadTemplate */ }
            }
        });
        newTemplateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                templateEditor = new TemplateEditor(mainPanel);
            }
        });
        defaultTemplateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String filename = "appData\\Metadata.dat";

                systemName = "Default";
                mEmulator.loadDefaultTemplate();
                currentMachineContent.setText(systemName);
                try {
                    FileWriter outFile = new FileWriter(filename);
                    BufferedWriter out = new BufferedWriter(outFile);
                    out.write(systemName);
                    out.close();
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "ERROR: Can't open " + filename + " for writing.");
                }
            }
        });
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                mEmulator.run(uCode.isSelected());
                if (uCode.isSelected()) {
                    commandContent.setText(mEmulator.getCycleDescription());
                } else {
                    commandContent.setText(mEmulator.getAssemblyCommand());
                }
                eContent.setText(mEmulator.getSystemComponent(E));
                acContent.setText(mEmulator.getSystemComponent(AC));
                irContent.setText(mEmulator.getSystemComponent(IR));
                drContent.setText(mEmulator.getSystemComponent(DR));
                pcContent.setText(mEmulator.getSystemComponent(PC));
                iContent.setText(mEmulator.getSystemComponent(I));
                arContent.setText(mEmulator.getSystemComponent(AR));
                tr0Content.setText(mEmulator.getSystemComponent(TR0));
                tr1Content.setText(mEmulator.getSystemComponent(TR1));
                fgiContent.setText(mEmulator.getSystemComponent(FGI));
                inprContent.setText(mEmulator.getSystemComponent(INPR));
                fgoContent.setText(mEmulator.getSystemComponent(FGO));
                outrContent.setText(mEmulator.getSystemComponent(OUTR));
                ienContent.setText(mEmulator.getSystemComponent(IEN));
                rContent.setText(mEmulator.getSystemComponent(R));
                setMemPanel(mEmulator.getSystemMemory());
            }
        });
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                mEmulator.reset();
                commandContent.setText("");
                eContent.setText("Undefined");
                acContent.setText("Undefined");
                irContent.setText("Undefined");
                drContent.setText("Undefined");
                pcContent.setText("Undefined");
                iContent.setText("Undefined");
                arContent.setText("Undefined");
                tr0Content.setText("Undefined");
                tr1Content.setText("Undefined");
                fgiContent.setText("Undefined");
                inprContent.setText("Undefined");
                fgoContent.setText("Undefined");
                outrContent.setText("Undefined");
                ienContent.setText("Undefined");
                rContent.setText("Undefined");
                setMemPanel(mEmulator.getSystemMemory());
            }
        });

    }

    public void setMemPanel(String[][] data) {

        String[] colNames = {"ADDR", "VALUE"};

        DefaultTableModel model = new DefaultTableModel(data, colNames) {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; // Disallow the editing of any cell.
            }
        };

        memTable.setModel(model);

    }

    public void setVarPanel() {

        String filename = "appData\\VarTable.csv";
        String[] colNames = {"NAME", "ADDR"};
        String[][] data = null;

        try {

            FileReader inputFile = new FileReader(filename);
            BufferedReader buffer = new BufferedReader(inputFile);
            String line;

            BufferedReader reader = new BufferedReader(new FileReader(filename));
            int lines = 0;
            while (reader.readLine() != null) ++lines;
            reader.close();

            data = new String[lines][2];

            for (int i = 0; i < lines; ++i) {

                line = buffer.readLine();
                String[] values = line.split(",");

                data[i][0] = values[0];
                data[i][1] = Value.toHexadecimal(values[1]);
            }

        } catch (FileNotFoundException fnf) {
            JOptionPane.showMessageDialog(null, "ERROR: Could not open " + filename + " for reading!");
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "ERROR: Could not read from " + filename + "!");
        }

        DefaultTableModel model = new DefaultTableModel(data, colNames) {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; // Disallow the editing of any cell.
            }
        };

        varTable.setModel(model);
        varTable.setAutoCreateRowSorter(true);
        varTable.getRowSorter().toggleSortOrder(1);

    }

    void setTemplateList(){

        File file = new File("Machines");
        String[] directories = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return new File(dir, name).isDirectory();
            }
        });
        DefaultListModel listModel = new DefaultListModel();
        for(String dir : directories){
            listModel.addElement(dir);
        }
        templateList.setModel(listModel);

    }

    public static void main(String[] args) {

        mainWindow gui = new mainWindow();

        String filename = "appData\\Metadata.dat";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            systemName = reader.readLine();
            reader.close();
        } catch (FileNotFoundException fnf) {
            JOptionPane.showMessageDialog(null, "ERROR: Could not open " + filename + " for reading!");
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "ERROR: Could not read from " + filename + "!");
        }

        mEmulator.getProgram();
        if (systemName.equals("Default")){
            mEmulator.loadDefaultTemplate();
            gui.currentMachineContent.setText(systemName);
        } else {
            try {
                mEmulator.loadTemplate(systemName);
                gui.currentMachineContent.setText(systemName);
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "ERROR: Can't load " + e.getMessage() + "! Loading default system.");
                systemName = "Default";
                mEmulator.loadDefaultTemplate();
                gui.currentMachineContent.setText(systemName);
                try {
                    FileWriter outFile = new FileWriter(filename);
                    BufferedWriter out = new BufferedWriter(outFile);
                    out.write(systemName);
                    out.close();
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "ERROR: Can't open " + filename + " for writing.");
                }
            } catch (Error e){
                JOptionPane.showMessageDialog(null, "ERROR: Can't load " + e.getMessage() + "! Loading default system.");
                systemName = "Default";
                mEmulator.loadDefaultTemplate();
                gui.currentMachineContent.setText(systemName);
                try {
                    FileWriter outFile = new FileWriter(filename);
                    BufferedWriter out = new BufferedWriter(outFile);
                    out.write(systemName);
                    out.close();
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "ERROR: Can't open " + filename + " for writing.");
                }
            }
        }
        gui.assembleButton.doClick();

        gui.setTemplateList();
        gui.memPanel.setPreferredSize(new Dimension(gui.memTable.getPreferredSize().width + 30, gui.memTable.getRowHeight() * 50));
        gui.varPanel.setPreferredSize(new Dimension(gui.varTable.getPreferredSize().width + 30, gui.varTable.getRowHeight() * 50));

        gui.pack();
        gui.setVisible(true);

    }

}
