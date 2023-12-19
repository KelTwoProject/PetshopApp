package com.crud.products;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.util.Arrays;

public class UserPanel {
    private static final String BELANJA_PASSWORD = "belanja"; // Replace with your actual belanja password
    private static final String CRUD_PASSWORD = "crud_password"; // Replace with your actual CRUD password

    public UserPanel() {
        belanjaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //SwingUtilities.invokeLater(UserPanel::createdKeranjangPanelUI);
                showPasswordDialog(BELANJA_PASSWORD, true);
            }
        });

        crudButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //SwingUtilities.invokeLater(UserPanel::createdDatainterfaceUI);
                showPasswordDialog(CRUD_PASSWORD, false);
            }
        });
    }

    private void showPasswordDialog(String correctPassword, boolean isBelanja) {
        JPasswordField passwordField = new JPasswordField();
        int result = JOptionPane.showConfirmDialog(null, passwordField, "Enter Password",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            char[] enteredPassword = passwordField.getPassword();
            if (checkPasswordAndOpenPanel(correctPassword, enteredPassword, isBelanja)) {
                // Password is correct, proceed with the corresponding UI
                if (isBelanja) {
                    SwingUtilities.invokeLater(UserPanel::createdKeranjangPanelUI);
                } else {
                    SwingUtilities.invokeLater(UserPanel::createdDatainterfaceUI);
                }
            } else {
                // Incorrect password, display an error message
                JOptionPane.showMessageDialog(null, "Incorrect password. Please try again.",
                        "Authentication Error", JOptionPane.ERROR_MESSAGE);
            }

            // Clear the password from memory to enhance security
            Arrays.fill(enteredPassword, ' ');
        }
    }

    // Method to check if the entered password is correct
    private boolean checkPasswordAndOpenPanel(String correctPassword, char[] enteredPassword, boolean isBelanja) {
        // Replace this logic with your actual password verification logic
        String enteredPasswordString = new String(enteredPassword);
        return correctPassword.equals(enteredPasswordString);
    }

    public JPanel getMainUserPanel(){
        return mainUserPanel;
    }
    private PreparedStatement preparedStatement;
    private static  void createdDatainterfaceUI(){
        Datainterface datainterfaceUI = new Datainterface();
        JPanel datainterfaceroot = datainterfaceUI.getMainPanel();

        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setContentPane(datainterfaceroot);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    private static void createdKeranjangPanelUI() {
        KeranjangPanel keranjangPanel = new KeranjangPanel();
        JPanel keranjangpanelUI = keranjangPanel.getMainKeranjangPanel();

        if (keranjangpanelUI != null) {
            JFrame jFrame = new JFrame();
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame.setContentPane(keranjangpanelUI);
            jFrame.pack();
            jFrame.setLocationRelativeTo(null);
            jFrame.setVisible(true);
        } else {
            System.err.println("keranjangpanelUI is null");
        }
    }

    private ImageIcon imageIcon;
    private JPanel mainUserPanel;
    private JButton belanjaButton;
    private JButton crudButton;
    private JPanel jFirstPanel;
    private JPanel jSecondPanel;
    private JLabel jTitleLabel;


}
