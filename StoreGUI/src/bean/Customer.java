package bean;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import customer.ManageCustomer;
import product.ProductDisplay;
import sign.CustomerLogin;
import swing.Theme;

public class Customer extends JFrame implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel panel;
    private JButton buttonLogout, buttonProfile, buttonProduct, buttonList;
    private JLabel title, header;

    public Customer() {
        super("Customer");

        this.setSize(Theme.GUI_WIDTH, Theme.GUI_HEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Theme.BACKGROUND_PANEL);

        title = new JLabel("Customer");
        title.setBounds(30, 40, 230, 75);
        title.setOpaque(true);
        title.setBorder(new EmptyBorder(0, 20, 0, 0));
        title.setFont(Theme.FONT_TITLE);
        title.setForeground(Theme.COLOR_TITLE);
        title.setBackground(Theme.BACKGROUND_TITLE);
        panel.add(title);

        buttonLogout = new JButton("Logout");
        buttonLogout.setBounds(Theme.GUI_WIDTH - 140, 40, Theme.BUTTON_PRIMARY_WIDTH, 30);
        buttonLogout.setFont(Theme.FONT_BUTTON);
        buttonLogout.setBackground(Color.WHITE);
        buttonLogout.setForeground(Theme.COLOR_TITLE);
        buttonLogout.addActionListener(this);
        panel.add(buttonLogout);

        buttonProduct = new JButton("Product");
        buttonProduct.setBounds(325, 250, 150, 50);
        buttonProduct.setFont(Theme.FONT_BUTTON);
        buttonProduct.setBackground(Theme.BACKGROUND_BUTTON_PRIMARY);
        buttonProduct.setForeground(Theme.COLOR_TITLE);
        buttonProduct.addActionListener(this);
        panel.add(buttonProduct);

        buttonProfile = new JButton("Profile");
        buttonProfile.setBounds(325, 350, 150, 50);
        buttonProfile.setFont(Theme.FONT_BUTTON);
        buttonProfile.setBackground(Theme.BACKGROUND_BUTTON_PRIMARY);
        buttonProfile.setForeground(Theme.COLOR_TITLE);
        buttonProfile.addActionListener(this);
        panel.add(buttonProfile);

        buttonList = new JButton("Shoping List");
        buttonList.setBounds(325, 450, 150, 50);
        buttonList.setFont(Theme.FONT_BUTTON);
        buttonList.setBackground(Theme.BACKGROUND_BUTTON_PRIMARY);
        buttonList.setForeground(Theme.COLOR_TITLE);
        buttonList.addActionListener(this);
        panel.add(buttonList);

        header = new JLabel();
        header.setBackground(Theme.BACKGROUND_HEADER);
        header.setOpaque(true);
        header.setBounds(0, 0, Theme.GUI_WIDTH, 75);
        panel.add(header);

        this.add(panel);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(buttonLogout)) {
            int logout = 0;
            logout = JOptionPane.showConfirmDialog(null, "You are sure of leaving", "Logout",
                    JOptionPane.YES_NO_OPTION);
            if (logout == JOptionPane.YES_OPTION) {
                this.setVisible(false);
                new CustomerLogin().setVisible(true);
            } else {
            }
        } else if (ae.getSource().equals(buttonProduct)) {
            this.setVisible(false);
            new ProductDisplay().setVisible(true);
        } else if (ae.getSource().equals(buttonProfile)) {
            this.setVisible(false);
            new Profile().setVisible(true);
        } else if (ae.getSource().equals(buttonList)) {
            this.setVisible(false);
            new ShopingList().setVisible(true);
        } else {
        }
    }
}
