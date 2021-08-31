package sign;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import DB.DatabaseClass;
import bean.Customer;
import main.Store;
import swing.Theme;

public class CustomerLogin extends JFrame implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel panel;
    private JButton buttonExit, buttonLogin, buttonSignup, buttonBack;
    private JLabel title, header, usernameLabel, passwordLabel;
    private JTextField usernameTF;
    private JPasswordField passwordF;
    public static String logedInCustomer = null;

    public CustomerLogin() {
        super("Customer Login");

        this.setSize(Theme.GUI_WIDTH, Theme.GUI_HEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Theme.BACKGROUND_PANEL);

        title = new JLabel("Customer Login");
        title.setBounds(30, 40, 340, 75);
        title.setOpaque(true);
        title.setBorder(new EmptyBorder(0, 20, 0, 0));
        title.setFont(Theme.FONT_TITLE);
        title.setForeground(Theme.COLOR_TITLE);
        title.setBackground(Theme.BACKGROUND_TITLE);
        panel.add(title);

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
        buttonBack.setForeground(Theme.COLOR_TITLE);
        buttonBack.addActionListener(this);
        panel.add(buttonBack);

        buttonSignup = new JButton("Signup");
        buttonSignup.setBounds(Theme.GUI_WIDTH - 140, 120, Theme.BUTTON_PRIMARY_WIDTH, 30);
        buttonSignup.setFont(Theme.FONT_BUTTON);
        buttonSignup.setBackground(Theme.BACKGROUND_TITLE);
        buttonSignup.setForeground(Theme.COLOR_TITLE);
        buttonSignup.addActionListener(this);
        panel.add(buttonSignup);

        usernameLabel = new JLabel("User ID: ");
        usernameLabel.setBounds(210, 280, 120, 30);
        usernameLabel.setFont(Theme.FONT_REGULAR);
        panel.add(usernameLabel);

        usernameTF = new JTextField();
        usernameTF.setBounds(330, 280, 220, 30);
        usernameTF.setFont(Theme.FONT_INPUT);
        panel.add(usernameTF);

        passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(210, 320, 120, 30);
        passwordLabel.setFont(Theme.FONT_REGULAR);
        panel.add(passwordLabel);

        passwordF = new JPasswordField();
        passwordF.setBounds(330, 320, 220, 30);
        passwordF.setFont(Theme.FONT_INPUT);
        panel.add(passwordF);

        buttonLogin = new JButton("Login");
        buttonLogin.setBounds(230, 400, 300, 30);
        buttonLogin.setFont(Theme.FONT_BUTTON);
        buttonLogin.setBackground(Theme.BACKGROUND_BUTTON_PRIMARY);
        buttonLogin.setForeground(Theme.COLOR_BUTTON_PRIMARY);
        buttonLogin.addActionListener(this);
        panel.add(buttonLogin);

        header = new JLabel();
        header.setBackground(Theme.BACKGROUND_HEADER);
        header.setOpaque(true);
        header.setBounds(0, 0, Theme.GUI_WIDTH, 75);
        panel.add(header);

        this.add(panel);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(buttonExit)) {
            System.exit(0);
        } else if (ae.getSource().equals(buttonLogin)) {
            String sql = "SELECT first_name, last_name, id  FROM customer WHERE username = ? AND password = ?";
            try {
                PreparedStatement statement = DatabaseClass.getInstance().getConnection().prepareStatement(sql);
                statement.setString(1, usernameTF.getText());
                statement.setString(2, passwordF.getText());
                ResultSet rs = statement.executeQuery();
                String firstName = null, lastName = null;
                while (rs.next()) {
                    firstName = rs.getString("first_name");
                    lastName = rs.getString("last_name");
                    logedInCustomer = rs.getString("id");
                }
                if (firstName != null) {
                    JOptionPane.showMessageDialog(null, "Login Succesful, Welcome " + firstName + " " + lastName);
                    this.setVisible(false);
                    new Customer().setVisible(true);
                } else
                    JOptionPane.showMessageDialog(null, "Invalid ID or Password");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (ae.getSource().equals(buttonBack)) {
            this.setVisible(false);
            new Store().setVisible(true);
        } else if (ae.getSource().equals(buttonSignup)) {
            this.setVisible(false);
            new CustomerReg().setVisible(true);
        } else {
        }
    }
}
