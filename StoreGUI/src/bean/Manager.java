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

import customer.CustomerInfo;
import emp.EmpInfo;
import product.ProductInfo;
import sign.ManagerLogin;
import swing.Theme;

public class Manager extends JFrame implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel panel;
    private JButton buttonLogout, buttonEmployee, buttonProduct, buttonCutomer;
    private JLabel title, header;

    public Manager() {
        super("Manager");

        this.setSize(Theme.GUI_WIDTH, Theme.GUI_HEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Theme.BACKGROUND_PANEL);

        title = new JLabel("Manager");
        title.setBounds(30, 40, 200, 75);
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

        buttonEmployee = new JButton("Employee");
        buttonEmployee.setBounds(325, 250, 150, 50);
        buttonEmployee.setFont(Theme.FONT_BUTTON);
        buttonEmployee.setBackground(Theme.BACKGROUND_BUTTON_PRIMARY);
        buttonEmployee.setForeground(Theme.COLOR_BUTTON_PRIMARY);
        buttonEmployee.addActionListener(this);
        panel.add(buttonEmployee);

        buttonCutomer = new JButton("Customer");
        buttonCutomer.setBounds(325, 350, 150, 50);
        buttonCutomer.setFont(Theme.FONT_BUTTON);
        buttonCutomer.setBackground(Theme.BACKGROUND_BUTTON_PRIMARY);
        buttonCutomer.setForeground(Theme.COLOR_BUTTON_PRIMARY);
        buttonCutomer.addActionListener(this);
        panel.add(buttonCutomer);

        buttonProduct = new JButton("Product");
        buttonProduct.setBounds(325, 450, 150, 50);
        buttonProduct.setFont(Theme.FONT_BUTTON);
        buttonProduct.setBackground(Theme.BACKGROUND_BUTTON_PRIMARY);
        buttonProduct.setForeground(Theme.COLOR_BUTTON_PRIMARY);
        buttonProduct.addActionListener(this);
        panel.add(buttonProduct);

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
                new ManagerLogin().setVisible(true);
            } else {
            }
        } else if (ae.getSource().equals(buttonEmployee)) {
            this.setVisible(false);
            new EmpInfo().setVisible(true);
        } else if (ae.getSource().equals(buttonCutomer)) {
            this.setVisible(false);
            new CustomerInfo().setVisible(true);
        } else if (ae.getSource().equals(buttonProduct)) {
            this.setVisible(false);
            new ProductInfo().setVisible(true);
        } else {
        }
    }
}
