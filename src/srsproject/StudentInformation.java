package srsproject;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.*;

public class StudentInformation extends JFrame implements ActionListener {
    JFrame StudentInformationFrame;
    JPanel headerPanel;
    JLabel headerLabel;
    JTable table;
    Color tableBorderColor;
    JTableHeader tableHeader;
    JScrollPane scrollPane;
    DefaultTableModel model;
    JButton addButton;
    JButton deleteButton;
    JButton backButton;
    JButton viewButton;
    JButton submitButton;
    JTextField searchBar;
    ImageIcon bg;
    Image imageSize;
    ImageIcon backgroundImage;
    JPanel panelImage;
    JLabel imageLabel;

    Connection conn;
    String dbUrl = "jdbc:mysql://localhost:3306/user_login";
    String dbUser = "root";
    String dbPassword = "rootmjv_root16";

    public StudentInformation() {
        StudentInformationFrame = new JFrame();
        StudentInformationFrame.setTitle("Student Information");
        StudentInformationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        StudentInformationFrame.setSize(1500, 1000);
        
        
        

        headerPanel = new JPanel();
        headerPanel.setBackground(new Color(128, 0, 0));
        headerPanel.setBounds(0, 0, 1500, 100);

        headerLabel = new JLabel("BSIT STUDENT INFORMATION RECORD");
        headerLabel.setPreferredSize(new Dimension(750, 100));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 38));
        headerLabel.setForeground(new Color(245, 245, 220));
        headerPanel.add(headerLabel);

        model = new DefaultTableModel(new Object[]{"Student ID", "Name", "Course", "Age", "Gender", "Contact Number", "Email"}, 0);
        table = new JTable(model);
        table.setOpaque(false);
        table.setBackground(new Color(245, 245, 220));

        tableHeader = table.getTableHeader();
        table.getTableHeader().setBackground(new Color(128, 0, 0));
        table.getTableHeader().setForeground(new Color(245, 245, 220));
        table.getTableHeader().setFont(new Font("Arial Black", Font.BOLD, 13));

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(35, 160, 1350, 500);

        tableBorderColor = new Color(128, 0, 0);
        scrollPane.setBorder(new LineBorder(tableBorderColor, 8));

        String[] choices = {"Male", "Female"};
        JComboBox<String> Gender = new JComboBox<>(choices);
        table.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(Gender));

        searchBar = new JTextField(20);
        searchBar.addActionListener(this);
        searchBar.setBounds(50, 120, 300, 30);
        

        addButton = new JButton("Add Row");
        addButton.setFont(new Font("Arial Black", Font.BOLD, 13));
        addButton.setForeground(new Color(128, 0, 0));
        addButton.setBackground(new Color(245, 245, 220));
        addButton.setOpaque(true);
        addButton.setBorder(null);
        addButton.setBounds(50, 680, 200, 50);
        addButton.addActionListener(this);

        deleteButton = new JButton("Delete Record");
        deleteButton.setFont(new Font("Arial Black", Font.BOLD, 13));
        deleteButton.setForeground(new Color(128, 0, 0));
        deleteButton.setBackground(new Color(245, 245, 220));
        deleteButton.setOpaque(true);
        deleteButton.setBorder(null);
        deleteButton.setBounds(270, 680, 200, 50);
        deleteButton.addActionListener(this);

        backButton = new JButton("BACK");
        backButton.setFont(new Font("Arial Black", Font.BOLD, 13));
        backButton.setForeground(new Color(128, 0, 0));
        backButton.setBackground(new Color(245, 245, 220));
        backButton.setOpaque(true);
        backButton.setBorder(null);
        backButton.setBounds(1180, 720, 200, 50);
        backButton.addActionListener(this);

        viewButton = new JButton("View Data");
        viewButton.setFont(new Font("Arial Black", Font.BOLD, 13));
        viewButton.setForeground(new Color(128, 0, 0));
        viewButton.setBackground(new Color(245, 245, 220));
        viewButton.setOpaque(true);
        viewButton.setBorder(null);
        viewButton.setBounds(490, 680, 200, 50);
        viewButton.addActionListener(this);

        submitButton = new JButton("Submit Data");
        submitButton.setFont(new Font("Arial Black", Font.BOLD, 13));
        submitButton.setForeground(new Color(128, 0, 0));
        submitButton.setBackground(new Color(245, 245, 220));
        submitButton.setOpaque(true);
        submitButton.setBorder(null);
        submitButton.setBounds(710, 680, 200, 50);
        submitButton.addActionListener(this);

        bg = new ImageIcon("folderimage/sbBinan.jpg");
        imageSize = bg.getImage().getScaledInstance(1500, 1000, Image.SCALE_SMOOTH);
        backgroundImage = new ImageIcon(imageSize);

        imageLabel = new JLabel(backgroundImage);

        JPanel panelImage = new JPanel();
        panelImage.setSize(1500, 1000);
        panelImage.add(imageLabel);

        StudentInformationFrame.add(scrollPane);
        StudentInformationFrame.add(headerPanel);
        StudentInformationFrame.add(addButton);
        StudentInformationFrame.add(deleteButton);
        StudentInformationFrame.add(backButton);
        StudentInformationFrame.add(viewButton);
        StudentInformationFrame.add(submitButton);
        StudentInformationFrame.add(searchBar);
        StudentInformationFrame.add(panelImage);

        StudentInformationFrame.pack();
        StudentInformationFrame.setVisible(true);


        connectToDatabase();
        loadData();
    }

    private void connectToDatabase() {
        try {
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to connect to database", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadData() {
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM student_information")) {
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("student_id"),
                        rs.getString("name"),
                        rs.getString("course"),
                        rs.getString("age"),
                        rs.getString("gender"),
                        rs.getString("contact_number"),
                        rs.getString("email")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addRowToDatabase(String student_id, String name, String course, String age, String gender, String contactNumber, String email) {
        String query = "INSERT INTO student_information (student_id, name, course, age, gender, contact_number, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, student_id);
            pstmt.setString(2, name);
            pstmt.setString(3, course);
            pstmt.setString(4, age);
            pstmt.setString(5, gender);
            pstmt.setString(6, contactNumber);
            pstmt.setString(7, email);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void searchInDatabase(String keyword) {
        model.setRowCount(0);
        String query = "SELECT * FROM student_information WHERE name LIKE ? OR student_id LIKE ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("student_id"),
                        rs.getString("name"),
                        rs.getString("course"),
                        rs.getString("age"),
                        rs.getString("gender"),
                        rs.getString("contact_number"),
                        rs.getString("email")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addRow() {
        model.addRow(new Object[]{"", "", "BSIT 2-1", "", "Set Gender", "", ""});
    }

    private void viewData() {
        model.setRowCount(0); 
        loadData(); 
    }

    private void deleteRowFromDatabase(String studentId) {
        String query = "DELETE FROM student_information WHERE student_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, studentId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void submitData() {
        int rowCount = model.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            String student_id = (String) model.getValueAt(i, 0);
            String name = (String) model.getValueAt(i, 1);
            String course = (String) model.getValueAt(i, 2);
            String age = (String) model.getValueAt(i, 3);
            String gender = (String) model.getValueAt(i, 4);
            String contactNumber = (String) model.getValueAt(i, 5);
            String email = (String) model.getValueAt(i, 6);
            addRowToDatabase(student_id, name, course, age, gender, contactNumber, email);
        }
        JOptionPane.showMessageDialog(this, "Data submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addRow();
        } else if (e.getSource() == deleteButton) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int confirmation = JOptionPane.showConfirmDialog(this, "Do you want to delete this record?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    String studentId = (String) table.getValueAt(selectedRow, 0);
                    deleteRowFromDatabase(studentId);
                    model.removeRow(selectedRow);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a record to delete", "No Record Selected", JOptionPane.WARNING_MESSAGE);
            }
            } else if (e.getSource() == searchBar) {
                String keyword = searchBar.getText();
                searchInDatabase(keyword);
            } else if (e.getSource() == viewButton) {
                viewData();
            } else if (e.getSource() == submitButton) {
                submitData();
            }
        if(e.getSource() == backButton){
           Dashboard db = new Dashboard();
           db.setVisible(true);
       }
    }

    
}
