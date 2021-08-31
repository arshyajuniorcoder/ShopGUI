package emp;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

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

public class AddEmployee extends JFrame implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel panel;
    private JButton buttonExit, buttonSubmit, buttonBack;
    private JLabel title, header, employeeUserLabel, passwordLabel, employeeFirstNameLabel, employeeLastNameLabel,
            employeeSexLabel, employeePhoneLabel, employeeAddressLabel, employeeTitleLabel, salaryLabel;
    private JTextField employeeUserTF, employeeFirstNameTF, employeeLastNameTF, employeeSalaryTF, employeePhoneTF,
            passwordF, employeeAddressTF;
    private JComboBox employeePhoneCB, employeeTitleCB;
    private JRadioButton male, female;
    private ButtonGroup btng;

    String[] str1 = {"93-AFG", "374-ARM", "994-AZE", "1-CAN", "86-CHN", "20-EGY", "33-FRA", "49-DEU", "91-IND",
            "98-IR", "39-ITA", "81-JPN", "7-RUS", "34-ESP", "90-TUR", "44-GBR", "1-USA"};
    String[] str2 = {"Manager", "Accountant", "Seller", "StoreKeeper"};

    public AddEmployee() {
        super("Create New Employee");

        this.setSize(Theme.GUI_WIDTH, Theme.GUI_HEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Theme.BACKGROUND_PANEL);

        title = new JLabel("Add Employee");
        title.setBounds(30, 40, 315, 75);
        title.setOpaque(true);
        title.setBorder(new EmptyBorder(0, 20, 0, 0));
        title.setFont(Theme.FONT_TITLE);
        title.setForeground(Theme.COLOR_TITLE);
        title.setBackground(Theme.BACKGROUND_TITLE);
        panel.add(title);

        employeeUserLabel = new JLabel("Username: ");
        employeeUserLabel.setBounds(60, 140, 140, 30);
        employeeUserLabel.setFont(Theme.FONT_REGULAR);
        panel.add(employeeUserLabel);

        passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(60, 190, 140, 30);
        passwordLabel.setFont(Theme.FONT_REGULAR);
        panel.add(passwordLabel);

        employeeFirstNameLabel = new JLabel("First Name: ");
        employeeFirstNameLabel.setBounds(60, 240, 140, 30);
        employeeFirstNameLabel.setFont(Theme.FONT_REGULAR);
        panel.add(employeeFirstNameLabel);

        employeeLastNameLabel = new JLabel("Last Name: ");
        employeeLastNameLabel.setBounds(60, 290, 140, 30);
        employeeLastNameLabel.setFont(Theme.FONT_REGULAR);
        panel.add(employeeLastNameLabel);

        employeeSexLabel = new JLabel("Sex: ");
        employeeSexLabel.setBounds(60, 340, 140, 30);
        employeeSexLabel.setFont(Theme.FONT_REGULAR);
        panel.add(employeeSexLabel);

        employeePhoneLabel = new JLabel("Phone No: ");
        employeePhoneLabel.setBounds(60, 390, 140, 30);
        employeePhoneLabel.setFont(Theme.FONT_REGULAR);
        panel.add(employeePhoneLabel);

        employeeAddressLabel = new JLabel("Address: ");
        employeeAddressLabel.setBounds(60, 440, 140, 30);
        employeeAddressLabel.setFont(Theme.FONT_REGULAR);
        panel.add(employeeAddressLabel);

        employeeTitleLabel = new JLabel("Title: ");
        employeeTitleLabel.setBounds(60, 490, 140, 30);
        employeeTitleLabel.setFont(Theme.FONT_REGULAR);
        panel.add(employeeTitleLabel);

        salaryLabel = new JLabel("Salary: ");
        salaryLabel.setBounds(60, 540, 140, 30);
        salaryLabel.setFont(Theme.FONT_REGULAR);
        panel.add(salaryLabel);

        employeeUserTF = new JTextField();
        employeeUserTF.setBounds(200, 140, 220, 30);
        employeeUserTF.setFont(Theme.FONT_INPUT);
        panel.add(employeeUserTF);

        passwordF = new JPasswordField();
        passwordF.setBounds(200, 190, 220, 30);
        passwordF.setFont(Theme.FONT_INPUT);
        panel.add(passwordF);

        employeeFirstNameTF = new JTextField();
        employeeFirstNameTF.setBounds(200, 240, 220, 30);
        employeeFirstNameTF.setFont(Theme.FONT_INPUT);
        panel.add(employeeFirstNameTF);

        employeeLastNameTF = new JTextField();
        employeeLastNameTF.setBounds(200, 290, 220, 30);
        employeeLastNameTF.setFont(Theme.FONT_INPUT);
        panel.add(employeeLastNameTF);

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

        employeePhoneCB = new JComboBox(str1);
        employeePhoneCB.setBounds(200, 390, 95, 30);
        employeePhoneCB.setFont(Theme.FONT_INPUT);
        panel.add(employeePhoneCB);

        employeePhoneTF = new JTextField();
        employeePhoneTF.setBounds(296, 390, 125, 30);
        employeePhoneTF.setFont(Theme.FONT_INPUT);
        panel.add(employeePhoneTF);

        employeeTitleCB = new JComboBox(str2);
        employeeTitleCB.setBounds(200, 490, 220, 30);
        employeeTitleCB.setFont(Theme.FONT_INPUT);
        panel.add(employeeTitleCB);

        employeeAddressTF = new JTextField();
        employeeAddressTF.setBounds(200, 440, 220, 30);
        employeeAddressTF.setFont(Theme.FONT_INPUT);
        panel.add(employeeAddressTF);

        employeeSalaryTF = new JTextField();
        employeeSalaryTF.setBounds(200, 540, 220, 30);
        employeeSalaryTF.setFont(Theme.FONT_INPUT);
        panel.add(employeeSalaryTF);

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
            new EmpInfo().setVisible(true);
        } else if (ae.getSource().equals(buttonExit)) {
            System.exit(0);
        } else if (ae.getSource().equals(buttonSubmit)) {
            try {
                String sql = "INSERT INTO employee (username, password, first_name, last_name, sex, phone_no, address, title, salary) values (?,?,?,?,?,?,?,?,?)";
                PreparedStatement statement = DatabaseClass.getInstance().getConnection().prepareStatement(sql);
                statement.setString(1, employeeUserTF.getText());
                statement.setString(2, passwordF.getText());
                statement.setString(3, employeeFirstNameTF.getText());
                statement.setString(4, employeeLastNameTF.getText());
                statement.setString(5, male.isSelected() ? "Male" : "Female");
                String selectedItem = (String) employeePhoneCB.getSelectedItem();
                String[] selectedItemArr = selectedItem.split("-");
                statement.setLong(6, Long.parseLong(selectedItemArr[0] + employeePhoneTF.getText()));
                statement.setString(7, employeeAddressTF.getText());
                statement.setString(8, (String) employeeTitleCB.getSelectedItem());
                statement.setLong(9, Long.parseLong(employeeSalaryTF.getText()));
                int row = statement.executeUpdate();
                if (row > 0) {
                    JOptionPane.showMessageDialog(null, "Submit Employee");
                    this.setVisible(false);
                    new EmpInfo().setVisible(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
        }
    }
}
