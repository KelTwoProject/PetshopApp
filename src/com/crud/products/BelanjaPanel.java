package com.crud.products;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BelanjaPanel {
    private double totalBelanja = 0.0;

    private JButton beliProdukButton;
    private JLabel jTitleLabelPanel;
    private JComboBox jCategoryComboBox;
    private JPanel mainBelanjaPanel;
    private JLabel jTotalBelanjaLabel;
    private JTextField jumlahTextField;
    private JPanel jListproduct1;
    private JPanel jListProduct;
    private JList jList1;
    private JLabel jKeranjangBelanjaLabel;
    private JPanel jKeranjangPanel;

    public BelanjaPanel() {
        // Inisialisasi JComboBox dengan pilihan kategori
        jCategoryComboBox.addItem("Makanan Kering");
        jCategoryComboBox.addItem("Makanan Basah");
        jCategoryComboBox.addItem("Aksesoris");

        jCategoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateListProduct();
            }
        });

        beliProdukButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedProduct = (String) jList1.getSelectedValue();
                double selectedProductPrice = getProductPrice(selectedProduct);
                int quantity = Integer.parseInt(jumlahTextField.getText());

                // Menambahkan produk ke dalam keranjang
                addTojKeranjangBelanja(selectedProduct, selectedProductPrice, quantity);
            }
        });
    }

    private void updateListProduct() {
        String selectedCategory = (String) jCategoryComboBox.getSelectedItem();

        DefaultListModel<String> model = new DefaultListModel<>();
        if ("Makanan Kering".equals(selectedCategory)) {
            // Tambahkan produk makanan kering ke dalam model
            model.addElement("Produk 1");
            model.addElement("Produk 2");
            // Tambahkan produk lainnya sesuai kebutuhan
        } else if ("Makanan Basah".equals(selectedCategory)) {
            // Tambahkan produk makanan basah ke dalam model
            model.addElement("Produk A");
            model.addElement("Produk B");
            // Tambahkan produk lainnya sesuai kebutuhan
        } else if ("Aksesoris".equals(selectedCategory)) {
            // Tambahkan produk aksesoris ke dalam model
            model.addElement("Aksesoris X");
            model.addElement("Aksesoris Y");
            // Tambahkan produk lainnya sesuai kebutuhan
        }
        jList1.setModel(model);
    }

    private void addTojKeranjangBelanja(String productName, double productPrice, int quantity) {
        // Membuat label untuk menampilkan informasi produk di keranjang
        JLabel productLabel = new JLabel(productName + " - Rp " + productPrice + " x " + quantity);

        // Menambahkan label produk ke dalam panel keranjang
        jKeranjangPanel.add(productLabel);

        // Menghitung total belanja
        totalBelanja += productPrice * quantity;

        // Menampilkan total belanja pada JLabel
        jTotalBelanjaLabel.setText("Total Belanja: Rp " + totalBelanja);

        // Memperbarui tampilan panel keranjang
        jKeranjangPanel.revalidate();
        jKeranjangPanel.repaint();
    }

    private double getProductPrice(String productName) {
        // Implementasikan logika untuk mendapatkan harga berdasarkan nama produk
        // Misalnya, Anda dapat menggunakan struktur data atau database untuk menyimpan harga produk
        // Dalam contoh ini, kita akan mengembalikan nilai acak
        return Math.random() * 100;
    }

    public JPanel getMainBelanjaPanel() {
        return mainBelanjaPanel;
    }
}
