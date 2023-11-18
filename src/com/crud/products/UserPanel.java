package com.crud.products;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

public class UserPanel {
    public UserPanel() {
        belanjaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            SwingUtilities.invokeLater(UserPanel::createdKeranjangPanelUI);
            }
        });
        crudButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(UserPanel::createdDatainterfaceUI);
                JFrame frame = new JFrame();
                frame.setPreferredSize(new Dimension(200,200));
            }
        });
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


    private JPanel mainUserPanel;
    private JButton belanjaButton;
    private JButton crudButton;
    private JPanel jFirstPanel;
    private JPanel jSecondPanel;
    private JLabel jTitleLabel;


}
