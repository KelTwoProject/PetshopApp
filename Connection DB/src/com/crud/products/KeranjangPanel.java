package com.crud.products;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class KeranjangPanel extends JFrame {
    class ProductListRenderer extends DefaultListCellRenderer {
        private static final int FIXED_WIDTH = 250;
        private static final int FIXED_HEIGHT = 20;
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            list.setFixedCellWidth(FIXED_WIDTH);
            list.setFixedCellHeight(FIXED_HEIGHT);

            return component;
        }
    }
    private Image backgroundImage;

    private JPanel mainPanel;
    private JPanel productPanel;
    private JPanel cartPanel;
    private JLabel titleLabel;
    private JButton foodButton;
    private JButton backButton;
    private JButton accessoryButton;
    private JComboBox<String> categoryComboBox;
    private DefaultListModel<Product> productListModel;
    private JList<Product> productList;
    private DefaultListModel<CartItem> cartListModel;
    private JList<CartItem> cartList;
    private JTextField quantityField;
    private JLabel totalLabel;

    private Map<String, List<Product>> productCategories = new HashMap<>();
    private Map<String, Integer> productPrices = new HashMap<>();
    private Map<Product, Integer> cartContents = new HashMap<>();
    private boolean isShoppingFood = true;
    private JPanel mainKeranjangPanel;

    class CartItem {
        private Product product;
        private int quantity;

        public CartItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        public Product getProduct() {
            return product;
        }

        public int getQuantity() {
            return quantity;
        }

        @Override
        public String toString() {
            return product.getName() + " x" + quantity + " - Rp " + quantity * product.getPrice();
        }
    }

    class Product {
        private String name;
        private int price;

        public Product(String name, int price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public int getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return name + " - Rp " + price;
        }
    }

    public KeranjangPanel() {

        setTitle("Aplikasi Petshop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLayout(new BorderLayout());

        ImageIcon background = new ImageIcon("C:\\Users\\hp\\Pictures\\Petshop.jpeg");
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setLayout(new BorderLayout());

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(0, 0, 0, 0)); // Set alpha to 0 for transparent background

        titleLabel = new JLabel("Selamat Datang Di KelTwo Petshop");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        foodButton = new JButton("Produk Makanan");
        foodButton.setBackground(Color.WHITE);
        accessoryButton = new JButton("Produk Aksesoris");
        accessoryButton.setBackground(Color.WHITE);
        backButton = new JButton("Back");
        backButton.setBackground(Color.WHITE);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(foodButton);
        buttonPanel.add(accessoryButton);
        buttonPanel.add(backButton);
        buttonPanel.setBackground(new Color(0,0,0,0));
        mainPanel.add(buttonPanel, BorderLayout.CENTER);


        backgroundLabel.add(mainPanel, BorderLayout.CENTER);
        setContentPane(backgroundLabel);

        add(mainPanel, BorderLayout.NORTH);

        productPanel = new JPanel();
        productPanel.setLayout(new BorderLayout());
        productPanel.setBackground(Color.PINK);

        categoryComboBox = new JComboBox<>(new String[]{"Makanan Basah", "Makanan Kering", "Aksesoris"});
        categoryComboBox.setBackground(Color.PINK);
        categoryComboBox.setForeground(Color.BLACK);
        productPanel.add(categoryComboBox, BorderLayout.NORTH);

        productListModel = new DefaultListModel<>();
        productList = new JList<>(productListModel);
        productList.setForeground(Color.BLACK);
        productPanel.add(new JScrollPane(productList), BorderLayout.CENTER);

        JPanel buyPanel = new JPanel();
        buyPanel.setLayout(new BorderLayout());
        quantityField = new JTextField(5);
        quantityField.setBorder(BorderFactory.createTitledBorder("Jumlah"));
        quantityField.setForeground(Color.BLACK);
        buyPanel.add(quantityField, BorderLayout.WEST);
        JButton buyButton = new JButton("Masukkan Keranjang");
        buyButton.setForeground(Color.BLACK);
        buyPanel.add(buyButton, BorderLayout.EAST);
        productPanel.add(buyPanel, BorderLayout.SOUTH);

        add(productPanel, BorderLayout.WEST);

        cartPanel = new JPanel();
        cartPanel.setLayout(new BorderLayout());
        cartPanel.setBackground(Color.PINK);

        JLabel cartLabel = new JLabel("Keranjang Belanja");
        cartLabel.setForeground(Color.BLACK);
        cartPanel.add(cartLabel, BorderLayout.NORTH);


        cartListModel = new DefaultListModel<>();
        cartList = new JList<>(cartListModel);
        cartList.setForeground(Color.BLUE);
        cartPanel.add(new JScrollPane(cartList), BorderLayout.CENTER);


        totalLabel = new JLabel("Total Belanja: Rp 0");
        totalLabel.setForeground(Color.BLACK);

        JButton purchaseButton = new JButton("Beli Produk");
        purchaseButton.setForeground(Color.BLACK);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setForeground(Color.BLACK);

        JPanel totalPanel = new JPanel(new BorderLayout());
        totalPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 0));
        totalPanel.add(cancelButton);

        totalPanel.add(purchaseButton);
        totalPanel.add(totalLabel, BorderLayout.WEST);
        totalPanel.add(cancelButton, BorderLayout.CENTER);
        totalPanel.add(purchaseButton, BorderLayout.EAST);
        cartPanel.add(totalPanel, BorderLayout.SOUTH);

        JPanel cancelPanel = new JPanel(new BorderLayout());
        cancelPanel.setForeground(Color.PINK);


        purchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cartContents.isEmpty()) {
                    JOptionPane.showMessageDialog(KeranjangPanel.this, "Keranjang belanja kosong. Masukkan produk terlebih dahulu.");
                } else {
                    // Menampilkan pesan transaksi berhasil dan tombol Oke
                    JOptionPane.showMessageDialog(KeranjangPanel.this, "Transaksi berhasil!");
                    int option = JOptionPane.showConfirmDialog(KeranjangPanel.this, "Ingin mencetak struk belanja?", "Cetak Struk", JOptionPane.YES_NO_OPTION);

                    // Menghandle pilihan iya atau tidak
                    if (option == JOptionPane.YES_OPTION) {
                        // Menampilkan pesan berhasil mencetak struk belanja
                        JOptionPane.showMessageDialog(KeranjangPanel.this, "Berhasil mencetak struk belanja!");
                    } else {
                        // Menampilkan pesan bahwa struk belanja tidak dicetak
                        JOptionPane.showMessageDialog(KeranjangPanel.this, "Struk belanja tidak dicetak.");
                    }
                }
            }
        });


        add(cartPanel, BorderLayout.EAST);

        initializeProductCategories();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComponent component = (JComponent) e.getSource();
                Window window = SwingUtilities.getWindowAncestor(component);
                window.dispose();
            }
        });

        foodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isShoppingFood = true;
                updateProductList("Makanan Basah");
            }
        });

        accessoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isShoppingFood = false;
                updateProductList("Aksesoris");
            }
        });

        categoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCategory = (String) categoryComboBox.getSelectedItem();
                updateProductList(selectedCategory);
            }
        });

        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = productList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Product selectedProduct = productListModel.getElementAt(selectedIndex);
                    int quantity = Integer.parseInt(quantityField.getText());

                    if (cartContents.containsKey(selectedProduct)) {
                        cartContents.put(selectedProduct, cartContents.get(selectedProduct) + quantity);
                    } else {
                        cartContents.put(selectedProduct, quantity);
                    }

                    cartListModel.clear();
                    for (Map.Entry<Product, Integer> entry : cartContents.entrySet()) {
                        Product product = entry.getKey();
                        int productQuantity = entry.getValue();
                        cartListModel.addElement(new CartItem(product, productQuantity));
                    }

                    int total = calculateTotalPrice(cartContents);
                    totalLabel.setText("Total Belanja: Rp " + total);

                    JOptionPane.showMessageDialog(KeranjangPanel.this, "Produk berhasil ditambahkan ke keranjang belanja!");
                } else {
                    JOptionPane.showMessageDialog(KeranjangPanel.this, "Pilih produk terlebih dahulu.");
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Panggil metode untuk menghapus item dari keranjang belanja
                cancelSelectedItem();
            }
            private void updateCartView() {
                cartListModel.clear();
                for (Map.Entry<Product, Integer> entry : cartContents.entrySet()) {
                    Product product = entry.getKey();
                    int productQuantity = entry.getValue();
                    cartListModel.addElement(new CartItem(product, productQuantity));
                }
            }
            private void cancelSelectedItem() {
                int selectedIndex = cartList.getSelectedIndex();
                if (selectedIndex != -1) {
                    CartItem selectedCartItem = cartListModel.getElementAt(selectedIndex);
                    Product selectedProduct = selectedCartItem.getProduct();

                    // Kurangi jumlah produk yang dihapus dari keranjang belanja
                    int quantityToRemove = selectedCartItem.getQuantity();
                    cartContents.put(selectedProduct, cartContents.get(selectedProduct) - quantityToRemove);

                    // Hapus item dari keranjang belanja jika jumlahnya menjadi 0 atau kurang
                    if (cartContents.get(selectedProduct) <= 0) {
                        cartContents.remove(selectedProduct);
                    }

                    // Perbarui tampilan keranjang belanja
                    updateCartView();

                    // Perbarui total belanja
                    int total = calculateTotalPrice(cartContents);
                    totalLabel.setText("Total Belanja: Rp " + total);
                } else {
                    JOptionPane.showMessageDialog(KeranjangPanel.this, "Pilih item yang akan dibatalkan.");
                }
            }
        });

        setVisible(true);
    }

    private void initializeProductCategories() {
        productCategories.put("Makanan Basah", new ArrayList<Product>() {
            {
                add(new Product("Whiskas Kucing", 15000));
                add(new Product("Life Cat", 11000));
                // Tambahkan produk makanan basah lainnya di sini
            }
        });

        productCategories.put("Makanan Kering", new ArrayList<Product>() {
            {
                add(new Product("Whiskas Kucing 1kg", 50000));
                add(new Product("Royal Canin Kucing 1kg", 250000));
                add(new Product("Proplan Kucing 1kg", 200000));
                add(new Product("Oricat Kucing 1kg", 20000));
                add(new Product("CatChoize Kucing 1kg", 19000));
                add(new Product("Nature Bridge Kucing 1kg", 230000));
                add(new Product("Bolt Kucing 1kg", 20000));
                add(new Product("Maxim kucing 1kg", 250000));
                add(new Product("Whiskas Anjing 1kg", 60000));
                add(new Product("Royal Canin Anjing 1kg", 300000));
                add(new Product("Proplan Anjing 1kg", 250000));
                add(new Product("Beauty Anjing 1kg", 27000));
                add(new Product("DogChoize Anjing 1kg", 23000));
                add(new Product("Nature Bridge Anjing 1kg", 250000));
                // Tambahkan produk makanan kering lainnya di sini
            }
        });

        productCategories.put("Aksesoris", new ArrayList<Product>() {
            {
                add(new Product("Kereta Anjing", 150000));
                add(new Product("Kalung Anjing", 15000));
                add(new Product("Kalung Kucing", 15000));
                add(new Product("Sisir Kutu Kucing", 5000));
                add(new Product("Pembersih Bulu", 50000));
                add(new Product("Gantungan Kunci Kucing", 10000));
                add(new Product("Gantungan Kayu", 20000));
                // Tambahkan produk aksesoris lainnya di sini
            }
        });

        // Inisialisasi harga produk
        // ...

        // Contoh inisialisasi harga produk:
        productPrices.put("Whiskas Kucing", 15000);
        productPrices.put("Life Cat", 11000);
        productPrices.put("Whiskas Kucing 1kg", 50000);
        productPrices.put("Royal Canin Kucing 1kg", 250000);
        productPrices.put("Proplan Kucing 1kg", 200000);
        productPrices.put("Oricat Kucing 1kg", 20000);
        productPrices.put("CatChoize Kucing 1kg", 19000);
        productPrices.put("Nature Bridge Kucing 1kg", 230000);
        productPrices.put("Bolt Kucing 1kg", 20000);
        productPrices.put("Maxim kucing 1kg", 2500000);
        productPrices.put("Whiskas Anjing 1kg", 60000);
        productPrices.put("Royal Canin Anjing 1kg", 300000);
        productPrices.put("Proplan Anjing 1kg", 250000);
        productPrices.put("Beauty Anjing 1kg", 27000);
        productPrices.put("DogChoize Anjing 1kg", 23000);
        productPrices.put("Nature Bridge Anjing 1kg", 250000);
        productPrices.put("Kereta Anjing", 150000);
        productPrices.put("Kalung Anjing", 15000);
        productPrices.put("Kalung Kucing", 15000);
        productPrices.put("Sisir Kutu Kucing", 5000);
        productPrices.put("Pembersih Bulu", 50000);
        productPrices.put("Gantungan Kunci", 10000);
        productPrices.put("Gantungan Kayu", 20000);
    }

    private void updateProductList(String category) {
        productListModel.clear();
        List<Product> products = productCategories.get(category);
        if (products != null) {
            for (Product product : products) {
                productListModel.addElement(product);
            }
        }
        productList.setCellRenderer(new ProductListRenderer());

        productList.setFixedCellWidth(ProductListRenderer.FIXED_WIDTH);
        productList.setFixedCellHeight(ProductListRenderer.FIXED_HEIGHT);
    }

    private int calculateTotalPrice(Map<Product, Integer> cartContents) {
        int totalPrice = 0;
        for (Map.Entry<Product, Integer> entry : cartContents.entrySet()) {
            int price = productPrices.getOrDefault(entry.getKey().getName(), 0);
            int quantity = entry.getValue();
            totalPrice += price * quantity;
        }
        return totalPrice;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new KeranjangPanel();
            }
        });
    }
    public JPanel getMainKeranjangPanel(){
        return mainKeranjangPanel;
    }
}


