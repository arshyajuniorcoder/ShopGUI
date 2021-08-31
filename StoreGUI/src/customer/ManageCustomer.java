package customer;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import DB.DatabaseClass;
import swing.Theme;

public class ManageCustomer extends JFrame implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel panel;
    private JButton buttonExit, buttonSubmit, buttonBack;
    private JLabel title, header, customerUserLabel, passwordLabel, customerFirstNameLabel, customerLastNameLabel,
            customerSexLabel, customerPhoneLabel, customerAddressLabel;
    private JTextField customerUserTF, customerFirstNameTF, customerLastNameTF, customerPhoneTF, passwordF,
            customerAddressTF;
    private JComboBox customerPhoneCB;
    private JRadioButton male, female;
    private ButtonGroup btng;

    String[] str1 = {"93-AFG", "374-ARM", "994-AZE", "1-CAN", "86-CHN", "20-EGY", "33-FRA", "49-DEU", "91-IND",
            "98-IR", "39-ITA", "81-JPN", "7-RUS", "34-ESP", "90-TUR", "44-GBR", "1-USA"};

    public ManageCustomer() {
        super("Edit Customer");
        editCustomer();
    }

    public void editCustomer() {

        this.setSize(Theme.GUI_WIDTH, Theme.GUI_HEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Theme.BACKGROUND_PANEL);

        title = new JLabel("Edit Customer");
        title.setBounds(30, 40, 315, 75);
        title.setOpaque(true);
        title.setBorder(new EmptyBorder(0, 20, 0, 0));
        title.setFont(Theme.FONT_TITLE);
        title.setForeground(Theme.COLOR_TITLE);
        title.setBackground(Theme.BACKGROUND_TITLE);
        panel.add(title);

        customerUserLabel = new JLabel("Username: ");
        customerUserLabel.setBounds(60, 140, 140, 30);
        customerUserLabel.setFont(Theme.FONT_REGULAR);
        panel.add(customerUserLabel);

        passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(60, 190, 140, 30);
        passwordLabel.setFont(Theme.FONT_REGULAR);
        panel.add(passwordLabel);

        customerFirstNameLabel = new JLabel("First Name: ");
        customerFirstNameLabel.setBounds(60, 240, 140, 30);
        customerFirstNameLabel.setFont(Theme.FONT_REGULAR);
        panel.add(customerFirstNameLabel);

        customerLastNameLabel = new JLabel("Last Name: ");
        customerLastNameLabel.setBounds(60, 290, 140, 30);
        customerLastNameLabel.setFont(Theme.FONT_REGULAR);
        panel.add(customerLastNameLabel);

        customerSexLabel = new JLabel("Sex: ");
        customerSexLabel.setBounds(60, 340, 140, 30);
        customerSexLabel.setFont(Theme.FONT_REGULAR);
        panel.add(customerSexLabel);

        customerPhoneLabel = new JLabel("Phone No: ");
        customerPhoneLabel.setBounds(60, 390, 140, 30);
        customerPhoneLabel.setFont(Theme.FONT_REGULAR);
        panel.add(customerPhoneLabel);

        customerAddressLabel = new JLabel("Address: ");
        customerAddressLabel.setBounds(60, 440, 140, 30);
        customerAddressLabel.setFont(Theme.FONT_REGULAR);
        panel.add(customerAddressLabel);

        customerUserTF = new JTextField();
        customerUserTF.setBounds(200, 140, 220, 30);
        customerUserTF.setFont(Theme.FONT_INPUT);
        panel.add(customerUserTF);

        passwordF = new JPasswordField();
        passwordF.setBounds(200, 190, 220, 30);
        passwordF.setFont(Theme.FONT_INPUT);
        panel.add(passwordF);

        customerFirstNameTF = new JTextField();
        customerFirstNameTF.setBounds(200, 240, 220, 30);
        customerFirstNameTF.setFont(Theme.FONT_INPUT);
        panel.add(customerFirstNameTF);

        customerLastNameTF = new JTextField();
        customerLastNameTF.setBounds(200, 290, 220, 30);
        customerLastNameTF.setFont(Theme.FONT_INPUT);
        panel.add(customerLastNameTF);

        male = new JRadioButton("Male");
        male.setBounds(200, 348, 80, 20);
        male.setSelected(false);
        male.setFont(Theme.FONT_INPUT);
        male.setBackground(Theme.BACKGROUND_PANEL);
        panel.add(male);

        female = new JRadioButton("Female");
        female.setBounds(280, 348, 80, 20);
        female.setSelected(false);
        female.setFont(Theme.FONT_INPUT);
        female.setBackground(Theme.BACKGROUND_PANEL);
        panel.add(female);

        btng = new ButtonGroup();
        btng.add(male);
        btng.add(female);

        customerPhoneCB = new JComboBox(str1);
        customerPhoneCB.setBounds(200, 390, 95, 30);
        customerPhoneCB.setFont(Theme.FONT_INPUT);
        panel.add(customerPhoneCB);

        customerPhoneTF = new JTextField();
        customerPhoneTF.setBounds(296, 390, 125, 30);
        customerPhoneTF.setFont(Theme.FONT_INPUT);
        panel.add(customerPhoneTF);

        customerAddressTF = new JTextField();
        customerAddressTF.setBounds(200, 440, 220, 30);
        customerAddressTF.setFont(Theme.FONT_INPUT);
        panel.add(customerAddressTF);

        String sql = "SELECT * FROM customer WHERE id=" + CustomerInfo.selectedCustomer;
        PreparedStatement statement;
        try {
            statement = DatabaseClass.getInstance().getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                customerUserTF.setText(rs.getString("username"));
                passwordF.setText(rs.getString("password"));
                customerFirstNameTF.setText(rs.getString("first_name"));
                customerLastNameTF.setText(rs.getString("last_name"));
                if (rs.getString("sex").equals("Male"))
                    male.setSelected(true);
                else
                    female.setSelected(true);

                for (int i = 0; i < str1.length; i++) {
                    String code = str1[i].split("-")[0];
                    if (rs.getString("phone_no").substring(0, code.length()).equals(code)) {
                        customerPhoneCB.setSelectedIndex(i);
                        customerPhoneTF.setText(
                                rs.getString("phone_no").substring(code.length(), rs.getString("phone_no").length()));
                        break;
                    }

                }
                customerAddressTF.setText(rs.getString("address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
            new CustomerInfo().setVisible(true);
        } else if (ae.getSource().equals(buttonExit)) {
            System.exit(0);
        } else if (ae.getSource().equals(buttonSubmit)) {
            try {
                String sql = "UPDATE customer set username=?, password=?, first_name=?, last_name=?, sex=?, phone_no=?, address=? WHERE id="
                        + CustomerInfo.selectedCustomer;
                PreparedStatement statement = DatabaseClass.getInstance().getConnection().prepareStatement(sql);
                statement.setString(1, customerUserTF.getText());
                statement.setString(2, passwordF.getText());
                statement.setString(3, customerFirstNameTF.getText());
                statement.setString(4, customerLastNameTF.getText());
                statement.setString(5, male.isSelected() ? "Male" : "Female");
                String selectedItem = (String) customerPhoneCB.getSelectedItem();
                String[] selectedItemArr = selectedItem.split("-");
                statement.setLong(6, Long.parseLong(selectedItemArr[0] + customerPhoneTF.getText()));
                statement.setString(7, customerAddressTF.getText());
                int row = statement.executeUpdate();
                if (row > 0) {
                    JOptionPane.showMessageDialog(null, "Update Customer Information");
                    this.setVisible(false);
                    new CustomerInfo().setVisible(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
        }
    }
}
