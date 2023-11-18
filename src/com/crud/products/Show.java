package com.crud.products;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;

public class Show {
    public static void main(String[]args){
        SwingUtilities.invokeLater(Show::createGUI);

    }

    public static void createGUI(){
        UserPanel UI = new UserPanel();
        JPanel root = UI.getMainUserPanel();

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(root);
        frame.setPreferredSize(new Dimension(600,350));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
