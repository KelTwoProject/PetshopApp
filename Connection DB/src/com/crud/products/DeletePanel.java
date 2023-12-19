package com.crud.products;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class DeletePanel {
    public DeletePanel() {
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            String userId;
            userId = jTextFieldId.getText();
            if (!Objects.equals(userId,"")){
                try {
                    preparedStatement = Connector.connectDB().prepareStatement("DELETE FROM products WHERE products_id=?;");
                    preparedStatement.setString(1, userId);
                    preparedStatement.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Data Berhasil Dihapus");
                    JComponent component = (JComponent) e.getSource();
                    Window window = SwingUtilities.getWindowAncestor(component);
                    window.dispose();
                } catch (SQLException err){
                    err.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null,"Masih ada yang kosong");
            }

            }
        });
        cancleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComponent component = (JComponent) e.getSource();
                Window window = SwingUtilities.getWindowAncestor(component);
                window.dispose();
            }
        });
    }

    public JPanel getDeletePanel(){
        return deletePanel;
    }
    private PreparedStatement preparedStatement;
    private JPanel deletePanel;
    private JLabel jdeleteLabelPanel;
    private JPanel jPanelID;
    private JPanel jPanelButton;
    private JTextField jTextFieldId;
    private JButton cancleButton;
    private JButton deleteButton;
    private JLabel jIdDeleteLabel;
    private JLabel jIdLabel;
}
