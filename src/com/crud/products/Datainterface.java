package com.crud.products;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Datainterface {

    public Datainterface() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productsname, productsprice, productscategory;
                productsname = jTextFieldName.getText();
                productsprice = jTextFieldPrice.getText();
                productscategory = jTextFieldCategory.getText();

                try {
                    preparedStatement =  Connector.connectDB().prepareStatement("INSERT INTO products (products_name, products_price, products_category) values (?, ?, ?);");
                    preparedStatement.setString(1,productsname);
                    preparedStatement.setString(2,productsprice);
                    preparedStatement.setString(3,productscategory);
                    preparedStatement.executeUpdate();
                    showData();
                    JOptionPane.showMessageDialog(null, "Data Berhasil Dimasukkan");
                } catch (SQLException err){
                    Logger.getLogger(Datainterface.class.getName()).log(Level.SEVERE, null, err);

                }
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(Datainterface::createUpdateGUI);
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(Datainterface::createDeleteGUI);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComponent component = (JComponent) e.getSource();
                Window window = SwingUtilities.getWindowAncestor(component);
                window.dispose();
            }
        });
    }
    public static void createuserGUI(){

    }
    public JPanel getMainPanel(){
        showData();
        return mainPanel;}

    private DefaultTableModel tableModel;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    private void showData(){
        try {
            Object[] columnTitle = {"Products ID", "Name", "Price", "Category"};
            tableModel = new DefaultTableModel(null, columnTitle);
            jTable.setModel(tableModel);

            //retrieve connection from DB
            Connection connection = Connector.connectDB();
            Statement statement = connection.createStatement();
            tableModel.getDataVector().removeAllElements();

            //initiate result with SQL Query
            resultSet = statement.executeQuery("SELECT * FROM products");
            while (resultSet.next()){
                Object[] data = {
                        resultSet.getString("products_id"),
                        resultSet.getString("products_name"),
                        resultSet.getString("products_price"),
                        resultSet.getString("products_category")
                };
                tableModel.addRow(data);
            };

        } catch (SQLException err){
            throw new RuntimeException(err);

        }
    }

    private static void createUpdateGUI(){
        UpdatePanel updateUI = new UpdatePanel();
        JPanel updateRoot = updateUI.getUpdatePanel();

        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setContentPane(updateRoot);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }
    private static void createDeleteGUI(){
        DeletePanel deleteUI = new DeletePanel();
        JPanel deleteRoot = deleteUI.getDeletePanel();

        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setContentPane(deleteRoot);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    private JPanel mainPanel;
    private JLabel jTitlePanel;
    private JTextField jTextFieldName;
    private JTextField jTextFieldPrice;
    private JTextField jTextFieldCategory;
    private JTable jTable;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JPanel jFirstPanel;
    private JPanel jSecondPanel;
    private JPanel jThridPanel;
    private JLabel jLabelName;
    private JLabel jLabelPrice;
    private JLabel jLabelCategory;
    private JScrollPane jScrolls;
    private JButton backButton;
}
