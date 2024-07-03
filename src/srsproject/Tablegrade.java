package srsproject;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.border.*;
import java.sql.*;
import java.util.*;

public class Tablegrade implements ActionListener {
    private JFrame tblGrades;
    private JButton btnReturn, btnEdit, btnAdd, btnDelete;
    private JTable table;
    private DefaultTableModel tableModel;

    private static final String dbURL = "jdbc:mysql://localhost:3306/user_login";
    private static final String dbUser = "root";
    private static final String dbPass = "rootmjv_root16";
    
    public Tablegrade() {
        tblGrades = new JFrame();
        tblGrades.setSize(1500, 1000);
        tblGrades.setTitle("Grades");
        tblGrades.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tblGrades.setLayout(null);
        
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(128, 0, 0));
        headerPanel.setBounds(0, 0, 1500, 100);

        String[] columnHeader = {"Student Name", "COMP 009", "COMP 010", "COMP 012", "COMP 013", "COMP 014", "ELEC IT-FE2", "INTE 202", "PAHFIT 4", "GWA"};
        String[][] data = display();
        
        tableModel = new DefaultTableModel(data, columnHeader);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(75, 160, 1350, 500);

        table.getTableHeader().setBackground(new Color(128, 0, 0));
        table.getTableHeader().setForeground(new Color(245, 245, 220));
        table.getTableHeader().setFont(new Font("Arial Black", Font.BOLD, 13));

        Color tableBorderColor = new Color(128, 0, 0);
        scrollPane.setBorder(new LineBorder(tableBorderColor, 8));

        JLabel lblHeader = new JLabel("BSIT STUDENT GRADE SYSTEM");
        lblHeader.setBounds(400, 30, 700, 50);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 38));
        lblHeader.setForeground(Color.WHITE);

        btnReturn = new JButton("RETURN TO MAIN MENU");
        btnReturn.setBounds(1100, 675, 300, 50);
        btnReturn.setFont(new Font("Arial Black", Font.BOLD, 13));
        btnReturn.setBackground(new Color(245, 245, 220));
        btnReturn.setForeground(new Color(128, 0, 0));
        btnReturn.addActionListener(this);

        btnAdd = new JButton("ADD");
        btnAdd.setBounds(100, 675, 200, 50);
        btnAdd.setFont(new Font("Arial Black", Font.BOLD, 13));
        btnAdd.setBackground(new Color(245, 245, 220));
        btnAdd.setForeground(new Color(128, 0, 0));
        btnAdd.addActionListener(this);

        btnDelete = new JButton("DELETE");
        btnDelete.setBounds(350, 675, 200, 50);
        btnDelete.setFont(new Font("Arial Black", Font.BOLD, 13));
        btnDelete.setBackground(new Color(245, 245, 220));
        btnDelete.setForeground(new Color(128, 0, 0));
        btnDelete.addActionListener(this);

        JLabel background = new JLabel();
        ImageIcon bckgrndPic = new ImageIcon("folderimage/sbBinan.jpg");
        background.setIcon(bckgrndPic);
        background.setSize(1500, 1000);

        tblGrades.add(btnReturn);
        tblGrades.add(btnAdd);
        tblGrades.add(btnDelete);
        tblGrades.add(scrollPane);
        tblGrades.add(lblHeader);
        tblGrades.add(headerPanel);
        tblGrades.add(background);
        tblGrades.setVisible(true);
    }

    private String[][] display() {
        ArrayList<String[]> dataList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(dbURL, dbUser, dbPass)) {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM gradesystem");
            while (rs.next()) {
                String[] row = new String[10];
                row[0] = rs.getString("Student_Name");
                row[1] = rs.getString("COMP090");
                row[2] = rs.getString("COMP010");
                row[3] = rs.getString("COMP012");
                row[4] = rs.getString("COMP013");
                row[5] = rs.getString("COMP014");
                row[6] = rs.getString("ELECIT_FE2");
                row[7] = rs.getString("INTE202");
                row[8] = rs.getString("PATHFIT4");
                row[9] = rs.getString("GWA");                
                dataList.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[][] data = new String[dataList.size()][10];
        for (int i = 0; i < dataList.size(); i++) {
            data[i] = dataList.get(i);
        }
        return data;
    }

    private void delete(String studentName) {
        try (Connection connection = DriverManager.getConnection(dbURL, dbUser, dbPass)) {
            String sql = "DELETE FROM gradesystem WHERE Student_Name=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, studentName);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "Row deleted successfully from database!", "Delete", JOptionPane.INFORMATION_MESSAGE);
                refreshTable();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void refreshTable() {
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }
        
        String[][] newData = display();
        
        for (String[] row : newData) {
            tableModel.addRow(row);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            Addgrade g = new Addgrade();
        } else if (e.getSource() == btnDelete) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) { 
                String studentName = (String) table.getValueAt(selectedRow, 0);
                delete(studentName);
            }
        } else if (e.getSource() == btnReturn) {
            tblGrades.dispose(); 
            Dashboard dashboard = new Dashboard(); 
        }
    }

    
}
