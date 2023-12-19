package com.crud.products;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class UpdatePanel {

    public UpdatePanel() {
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userId, userName, userPrice, userCategory;
                userId = jProductID.getText();
                userName = jTextName.getText();
                userPrice = jTextPrice.getText();
                userCategory = jTextCategory.getText();
                if (!Objects.equals(userId,"") && !Objects.equals(userName,"") && !Objects.equals(userPrice,"") && !Objects.equals(userCategory, "")){
                    try {
                        preparedStatement = Connector.connectDB().prepareStatement("UPDATE products SET products_name=?, products_price=?, products_category=? WHERE products_id=?;");
                        preparedStatement.setString(1, userName);
                        preparedStatement.setString(2, userPrice);
                        preparedStatement.setString(3, userCategory);
                        preparedStatement.setString(4, userId);
                        preparedStatement.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Data Berhasil DiUpdate");
                        JComponent component = (JComponent) e.getSource();
                        Window window = SwingUtilities.getWindowAncestor(component);
                        window.dispose();
                    } catch (SQLException exception){
                        exception.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"Masih ada yang kososng");
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

    public JPanel getUpdatePanel() {
        return mainUpdatePanel;
    }

    private PreparedStatement preparedStatement;
    private JPanel mainUpdatePanel;
    private JLabel jTitleUpdateLabel;
    private JTextField jProductID;
    private JTextField jTextName;
    private JTextField jTextPrice;
    private JTextField jTextCategory;
    private JButton cancleButton;
    private JButton updateButton;
    private JLabel jPanelLabel;
    private JLabel jIdLabel;
    private JLabel jNameLabel;
    private JLabel jPriceLabel;
    private JLabel jCategoryLabel;
}
