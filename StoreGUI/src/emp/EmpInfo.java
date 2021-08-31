package emp;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DB.DatabaseClass;
import bean.Manager;
import swing.Theme;

public class EmpInfo extends JFrame implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel panel;
    private JScrollPane scroll;
    private JComboBox byWhatCB;
    private JTable table;
    private JButton buttonExit, buttonBack, buttonCheck, buttonAdd, buttonEdit, buttonDelete;
    private JLabel title, header, keywordLabel;
    private JTextField keywordTF;
    public static String selectedEmp = null;
    private DefaultTableModel model;

    public EmpInfo() {
        super("Employee Information");

        this.setSize(Theme.GUI_WIDTH, Theme.GUI_HEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Theme.BACKGROUND_PANEL);

        title = new JLabel("Employee");
        title.setBounds(30, 40, 230, 75);
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
        buttonBack.setForeground(Theme.COLOR_BUTTON_PRIMARY);
        buttonBack.addActionListener(this);
        panel.add(buttonBack);

        buttonEdit = new JButton("Edit");
        buttonEdit.setBounds(Theme.GUI_WIDTH - 140, 120, Theme.BUTTON_PRIMARY_WIDTH, 30);
        buttonEdit.setFont(Theme.FONT_BUTTON);
        buttonEdit.setBackground(Theme.BACKGROUND_BUTTON_PRIMARY);
        buttonEdit.setForeground(Theme.COLOR_BUTTON_PRIMARY);
        buttonEdit.addActionListener(this);
        panel.add(buttonEdit);

        buttonDelete = new JButton("Delete");
        buttonDelete.setBounds(Theme.GUI_WIDTH - 140, 160, Theme.BUTTON_PRIMARY_WIDTH, 30);
        buttonDelete.setFont(Theme.FONT_BUTTON);
        buttonDelete.setBackground(Theme.BACKGROUND_BUTTON_PRIMARY);
        buttonDelete.setForeground(Theme.COLOR_BUTTON_PRIMARY);
        buttonDelete.addActionListener(this);
        panel.add(buttonDelete);

        buttonAdd = new JButton("Add");
        buttonAdd.setBounds(Theme.GUI_WIDTH - 140, 200, Theme.BUTTON_PRIMARY_WIDTH, 30);
        buttonAdd.setFont(Theme.FONT_BUTTON);
        buttonAdd.setBackground(Theme.BACKGROUND_BUTTON_PRIMARY);
        buttonAdd.setForeground(Theme.COLOR_BUTTON_PRIMARY);
        buttonAdd.addActionListener(this);
        panel.add(buttonAdd);

        keywordLabel = new JLabel("Keyword: ");
        keywordLabel.setBounds(60, 140, 140, 30);
        keywordLabel.setFont(Theme.FONT_REGULAR);
        panel.add(keywordLabel);

        keywordTF = new JTextField();
        keywordTF.setBounds(160, 140, 240, 30);
        keywordTF.setFont(Theme.FONT_INPUT);
        panel.add(keywordTF);

        byWhatCB = new JComboBox(new Object[]{"By ID", "By Name"});
        byWhatCB.setBounds(400, 140, 100, 29);
        byWhatCB.setFont(Theme.FONT_INPUT);
        panel.add(byWhatCB);

        buttonCheck = new JButton("Search");
        buttonCheck.setBounds(500, 140, Theme.BUTTON_PRIMARY_WIDTH, 30);
        buttonCheck.setFont(Theme.FONT_BUTTON);
        buttonCheck.setBackground(Theme.BACKGROUND_BUTTON_PRIMARY);
        buttonCheck.setForeground(Theme.COLOR_BUTTON_PRIMARY);
        buttonCheck.addActionListener(this);
        panel.add(buttonCheck);

        table = new JTable();
        table.setDefaultEditor(Object.class, null);
        scroll = new JScrollPane(table);
        scroll.setBounds(40, 185, 600, 300);
        panel.add(scroll);

        this.setTableData(0, null);

        header = new JLabel();
        header.setBackground(Theme.BACKGROUND_HEADER);
        header.setOpaque(true);
        header.setBounds(0, 0, Theme.GUI_WIDTH, 75);
        panel.add(header);

        this.add(panel);
    }

    private void setTableData(int id, String name) {
        model = new DefaultTableModel(new String[]{"ID", "Username", "First Name", "Last Name", "Sex", "Phone no.",
                "Address", "Title", "Salary"}, 0);
        String sql = "SELECT * FROM employee ";
        boolean nameWhere = false;
        if (id != 0 || name != null) {
            sql += "WHERE ";
            if (id != 0) {
                sql += "id = " + id + " ";
            }
            if (name != null) {
                nameWhere = true;
                sql += "first_name = ? ";
            }
        }
        PreparedStatement statement;
        try {
            statement = DatabaseClass.getInstance().getConnection().prepareStatement(sql);
            if (nameWhere)
                statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                model.addRow(new String[]{rs.getString("id"), rs.getString("username"), rs.getString("first_name"),
                        rs.getString("last_name"), rs.getString("sex"), rs.getString("phone_no"),
                        rs.getString("address"), rs.getString("title"), rs.getString("salary")});
            }
            table.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(buttonExit)) {
            System.exit(0);
        } else if (ae.getSource().equals(buttonBack)) {
            this.setVisible(false);
            new Manager().setVisible(true);
        } else if (ae.getSource().equals(buttonEdit)) {
            try {
                selectedEmp = model.getValueAt(table.getSelectedRow(), 0).toString();
                this.setVisible(false);
                new ManageEmployee().setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Please select an option");
            }
        } else if (ae.getSource().equals(buttonDelete)) {
            this.selectedEmp = null;
            try {
                String sql = "Delete FROM employee where id=" + model.getValueAt(table.getSelectedRow(), 0).toString();
                PreparedStatement state;
                try {
                    state = DatabaseClass.getInstance().getConnection().prepareStatement(sql);
                    state.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                this.model.removeRow(table.getSelectedRow());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Please select an option");
            }
        } else if (ae.getSource().equals(buttonAdd)) {
            this.setVisible(false);
            new AddEmployee().setVisible(true);
        } else if (ae.getSource().equals(buttonCheck)) {
            if (keywordTF.getText().length() > 0) {
                if (byWhatCB.getSelectedIndex() == 0) {
                    try {
                        int i = Integer.parseInt(keywordTF.getText());
                        this.setTableData(i, null);
                    } catch (Exception e) {

                    }
                } else {
                    this.setTableData(0, keywordTF.getText());
                }
            } else {
                this.setTableData(0, null);
            }
        } else {
        }
    }
}