package product;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import DB.DatabaseClass;
import swing.Theme;

public class AddProduct extends JFrame implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel panel;
    private JButton buttonExit, buttonSubmit, buttonBack;
    private JLabel title, header, productCodeLabel, productNameLabel, priceLabel, quantityLabel;
    private JTextField productCodeTF, productNameTF, priceTF, quantityTF;

    public AddProduct() {
        super("Create New Product");

        this.setSize(Theme.GUI_WIDTH, Theme.GUI_HEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Theme.BACKGROUND_PANEL);

        title = new JLabel("New Product");
        title.setBounds(30, 40, 280, 75);
        title.setOpaque(true);
        title.setBorder(new EmptyBorder(0, 20, 0, 0));
        title.setFont(Theme.FONT_TITLE);
        title.setForeground(Theme.COLOR_TITLE);
        title.setBackground(Theme.BACKGROUND_TITLE);
        panel.add(title);

        productCodeLabel = new JLabel("Product Code: ");
        productCodeLabel.setBounds(60, 140, 140, 30);
        productCodeLabel.setFont(Theme.FONT_REGULAR);
        panel.add(productCodeLabel);

        productNameLabel = new JLabel("Product Name: ");
        productNameLabel.setBounds(60, 190, 140, 30);
        productNameLabel.setFont(Theme.FONT_REGULAR);
        panel.add(productNameLabel);

        priceLabel = new JLabel("Price: ");
        priceLabel.setBounds(60, 240, 140, 30);
        priceLabel.setFont(Theme.FONT_REGULAR);
        panel.add(priceLabel);

        quantityLabel = new JLabel("Quantity: ");
        quantityLabel.setBounds(60, 290, 140, 30);
        quantityLabel.setFont(Theme.FONT_REGULAR);
        panel.add(quantityLabel);

        productCodeTF = new JTextField();
        productCodeTF.setBounds(200, 140, 220, 30);
        productCodeTF.setFont(Theme.FONT_INPUT);
        panel.add(productCodeTF);

        productNameTF = new JTextField();
        productNameTF.setBounds(200, 190, 220, 30);
        productNameTF.setFont(Theme.FONT_INPUT);
        panel.add(productNameTF);

        priceTF = new JTextField();
        priceTF.setBounds(200, 240, 220, 30);
        priceTF.setFont(Theme.FONT_INPUT);
        panel.add(priceTF);

        quantityTF = new JTextField();
        quantityTF.setBounds(200, 290, 220, 30);
        quantityTF.setFont(Theme.FONT_INPUT);
        panel.add(quantityTF);

        buttonSubmit = new JButton("Submit");
        buttonSubmit.setBounds(480, 560, 200, 35);
        buttonSubmit.setFont(Theme.FONT_BUTTON);
        buttonSubmit.setBackground(Theme.BACKGROUND_BUTTON_PRIMARY);
        buttonSubmit.setForeground(Theme.COLOR_BUTTON_PRIMARY);
        buttonSubmit.addActionListener(this);
        panel.add(buttonSubmit);

        buttonExit = new JButton("Exit");
        buttonExit.setBounds(Theme.GUI_WIDTH - 140, 40, Theme.BUTTON_PRIMARY_WIDTH, 30);
        buttonExit.setFont(Theme.FONT_BUTTON);
        buttonExit.setBackground(Color.WHITE);
        buttonExit.setForeground(Theme.COLOR_TITLE);
        buttonExit.addActionListener(this);
        panel.add(buttonExit);

        buttonBack = new JButton("Back");
        buttonBack.setBounds(Theme.GUI_WIDTH - 140, 80, Theme.BUTTON_PRIMARY_WIDTH, 30);
        buttonBack.setFont(Theme.FONT_BUTTON);
        buttonBack.setBackground(Theme.BACKGROUND_BUTTON_PRIMARY);
        buttonBack.setForeground(Theme.COLOR_BUTTON_PRIMARY);
        buttonBack.addActionListener(this);
        panel.add(buttonBack);

        header = new JLabel();
        header.setBackground(Theme.BACKGROUND_HEADER);
        header.setOpaque(true);
        header.setBounds(0, 0, Theme.GUI_WIDTH, 75);
        panel.add(header);

        this.add(panel);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(buttonBack)) {
            this.setVisible(false);
            new ProductInfo().setVisible(true);
        } else if (ae.getSource().equals(buttonExit)) {
            System.exit(0);
        } else if (ae.getSource().equals(buttonSubmit)) {
            try {
                String sql = "INSERT INTO product (product_code, name, price, quantity) values (?,?,?,?)";
                PreparedStatement statement = DatabaseClass.getInstance().getConnection().prepareStatement(sql);
                statement.setString(1, productCodeTF.getText());
                statement.setString(2, productNameTF.getText());
                statement.setInt(3, Integer.parseInt(priceTF.getText()));
                statement.setInt(4, Integer.parseInt(quantityTF.getText()));
                int row = statement.executeUpdate();
                if (row > 0) {
                    JOptionPane.showMessageDialog(null, "Submit Product");
                    this.setVisible(false);
                    new ProductInfo().setVisible(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
        }
    }
}
