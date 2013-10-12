package GUI;

import Emulator.Global.Emulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TimeOutDialog extends JDialog {

    JPanel    contentPane;
    JButton   buttonStop;
    JButton   buttonCancel;
    JLabel    textArea;

    Emulator mEmulator;

    public TimeOutDialog(Emulator emulator) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonStop);

        mEmulator = emulator;

        buttonStop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onStop();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        pack();
        setTitle("Timeout Reached");
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width / 2) - (contentPane.getWidth() / 2);
        int y = (d.height / 3) - (contentPane.getHeight() / 2);
        setLocation(x, y);
        setVisible(true);

    }

    private void onStop() {
        mEmulator.stopProgram();
        dispose();
    }

    private void onCancel() {
        dispose();
    }
}
