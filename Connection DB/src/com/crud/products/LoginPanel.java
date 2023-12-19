package com.crud.products;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;


public class LoginPanel {

    public LoginPanel() {

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredUsername = textField1.getText();
                String enteredPassword = new String(passwordField1.getPassword());

                // Dummy usernames and passwords for KeranjangPanelUI and DatainterfaceUI
                String keranjangUsername = "Kasir";
                String keranjangPassword = "Keranjang";
                String datainterfaceUsername = "admin";
                String datainterfacePassword = "database";

                boolean keranjangCredentialsValid = enteredUsername.equals(keranjangUsername) && enteredPassword.equals(keranjangPassword);
                boolean datainterfaceCredentialsValid = enteredUsername.equals(datainterfaceUsername) && enteredPassword.equals(datainterfacePassword);

                if (keranjangCredentialsValid && datainterfaceCredentialsValid) {
                    JOptionPane.showMessageDialog(mainLoginPanel, "Ambiguous credentials. Please enter only one set of credentials.");
                } else if (keranjangCredentialsValid) {
                    createdKeranjangPanelUI();
                } else if (datainterfaceCredentialsValid) {
                    createdDatainterfaceUI();
                } else {
                    JOptionPane.showMessageDialog(mainLoginPanel, "username atau password salah");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginPanel::createLoginPanelGUI);
    }

    private static void createLoginPanelGUI() {
        LoginPanel loginUI = new LoginPanel();
        JPanel loginRoot = loginUI.getLoginPanel();

        JFrame jframe = new JFrame();
        jframe.setTitle("Aplikasi Petshop");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setContentPane(loginRoot);
        jframe.pack();
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
    }

    private JPanel getLoginPanel() {
        return mainLoginPanel;
    }

    private static  void createdDatainterfaceUI(){
        Datainterface datainterfaceUI = new Datainterface();
        JPanel datainterfaceroot = datainterfaceUI.getMainPanel();

        JFrame jFrame = new JFrame();
        jFrame.setTitle("Database PetShop");
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
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton loginButton;
    private JPanel mainLoginPanel;
    private JPanel LeftPanel;
    private JPanel RightPanel;
}
