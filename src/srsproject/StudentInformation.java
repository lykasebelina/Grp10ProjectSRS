package srsproject;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.*;

public class StudentInformation implements ActionListener {
    private JFrame studentInformationFrame;
    private JPanel headerPanel;
    private JLabel headerLabel;
    private JTable table;
    private Color tableBorderColor;
    private JTableHeader tableHeader;
    private JScrollPane scrollPane;
    private DefaultTableModel model;
    private JButton addButton;
    private JButton deleteButton;
    private JButton backButton;
    private JButton viewButton;
    private JButton submitButton;
    private JButton updateButton;
    private JTextField searchBar;
    private ImageIcon bg;
    private Image imageSize;
    private ImageIcon backgroundImage;
    private JPanel panelImage;
    private JLabel imageLabel;

    private Connection conn;
    private String dbUrl = "jdbc:mysql://localhost:3306/user_login";
    private String dbUser = "root";
    private String dbPassword = "rootmjv_root16";

    public StudentInformation() {
        studentInformationFrame = new JFrame();
        studentInformationFrame.setTitle("Student Information");
        studentInformationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        studentInformationFrame.setSize(1500, 1000);

        initializeComponents();
        connectToDatabase();
        loadData();

        studentInformationFrame.setVisible(true);
    }

    private void initializeComponents() {
        headerPanel = new JPanel();
        headerPanel.setBackground(new Color(128, 0, 0));
        headerPanel.setBounds(0, 0, 1500, 100);

        headerLabel = new JLabel("BSIT STUDENT INFORMATION RECORD");
        headerLabel.setPreferredSize(new Dimension(750, 100));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 38));
        headerLabel.setForeground(new Color(245, 245, 220));
        headerPanel.add(headerLabel);

        model = new DefaultTableModel(new Object[]{"Student ID", "Name", "Course", "Age", "Gender", "Contact Number", "Address", "Email"}, 0);
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
        
        updateButton = new JButton("Update Records");
        updateButton.setFont(new Font("Arial Black", Font.BOLD, 13));
        updateButton.setForeground(new Color(128, 0, 0));
        updateButton.setBackground(new Color(245, 245, 220));
        updateButton.setOpaque(true);
        updateButton.setBorder(null);
        updateButton.setBounds(930, 680, 200, 50);
        updateButton.addActionListener(this);

        bg = new ImageIcon("folderimage/sbBinan.jpg");
        imageSize = bg.getImage().getScaledInstance(1500, 1000, Image.SCALE_SMOOTH);
        backgroundImage = new ImageIcon(imageSize);

        imageLabel = new JLabel(backgroundImage);

        JPanel panelImage = new JPanel();
        panelImage.setSize(1500, 1000);
        panelImage.add(imageLabel);

        studentInformationFrame.add(scrollPane);
        studentInformationFrame.add(headerPanel);
        studentInformationFrame.add(addButton);
        studentInformationFrame.add(deleteButton);
        studentInformationFrame.add(backButton);
        studentInformationFrame.add(viewButton);
        studentInformationFrame.add(submitButton);
        studentInformationFrame.add(updateButton);
        studentInformationFrame.add(searchBar);
        studentInformationFrame.add(panelImage);

        studentInformationFrame.setLayout(null);
    }

    private void connectToDatabase() {
        try {
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(studentInformationFrame, "Failed to connect to database", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadData() {
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM student_info")) {
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("student_id"),
                        rs.getString("name"),
                        rs.getString("course"),
                        rs.getString("age"),
                        rs.getString("gender"),
                        rs.getString("contact_number"),
                        rs.getString("address"),
                        rs.getString("email")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addRowToDatabase(String student_id, String name, String course, String age, String gender, String contactNumber,String address, String email) {
        String query = "INSERT INTO student_info (student_id, name, course, age, gender, contact_number,address, email) VALUES (?, ?, ?, ?, ?, ?,?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, student_id);
            pstmt.setString(2, name);
            pstmt.setString(3, course);
            pstmt.setString(4, age);
            pstmt.setString(5, gender);
            pstmt.setString(6, contactNumber);
            pstmt.setString(7, address);
            pstmt.setString(8, email);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void searchInDatabase(String keyword) {
        model.setRowCount(0);
        String query = "SELECT * FROM student_info WHERE name LIKE ? OR student_id LIKE ?";
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
                        rs.getString("address"),
                        rs.getString("email")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addRow() {
        model.addRow(new Object[]{"", "", "BSIT 2-1", "", "Set Gender", "", "", ""});
    }

    private void viewData() {
        model.setRowCount(0); 
        loadData(); 
    }

    private void deleteRowFromDatabase(String studentId) {
        String query = "DELETE FROM student_info WHERE student_id = ?";
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
        String address = (String) model.getValueAt(i, 6);
        String email = (String) model.getValueAt(i, 7);

      
        if (!isStudentIdExists(student_id)) {
            addRowToDatabase(student_id, name, course, age, gender, contactNumber, address, email);
        } 
    }
}



    
    private boolean isStudentIdExists(String student_id) {
    String query = "SELECT * FROM student_info WHERE student_id = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setString(1, student_id);
        try (ResultSet rs = pstmt.executeQuery()) {
            return rs.next(); 
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    
    
    
    private void updateData() {
    int rowCount = model.getRowCount();
    for (int i = 0; i < rowCount; i++) {
        String student_id = (String) model.getValueAt(i, 0);
        String name = (String) model.getValueAt(i, 1);
        String course = (String) model.getValueAt(i, 2);
        String age = (String) model.getValueAt(i, 3);
        String gender = (String) model.getValueAt(i, 4);
        String contactNumber = (String) model.getValueAt(i, 5);
        String address = (String) model.getValueAt(i, 6);
        String email = (String) model.getValueAt(i, 7);

      
        updateRowInDatabase(student_id, name, course, age, gender, contactNumber, address, email);
    }
}

private void updateRowInDatabase(String student_id, String name, String course, String age, String gender, String contactNumber, String address, String email) {
    String query = "UPDATE student_info SET name = ?, course = ?, age = ?, gender = ?, contact_number = ?, address = ?, email = ? WHERE student_id = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setString(1, name);
        pstmt.setString(2, course);
        pstmt.setString(3, age);
        pstmt.setString(4, gender);
        pstmt.setString(5, contactNumber);
        pstmt.setString(6, address);
        pstmt.setString(7, email);
        pstmt.setString(8, student_id);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            studentInformationFrame.dispose(); 
            Dashboard db = new Dashboard();
        } else if (e.getSource() == addButton) {
            addRow();
        } else if (e.getSource() == deleteButton) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String studentId = (String) model.getValueAt(selectedRow, 0);
                deleteRowFromDatabase(studentId);
                model.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(studentInformationFrame, "Select a row to delete", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == viewButton) {
            viewData();
        } else if (e.getSource() == submitButton) {
            submitData();
            JOptionPane.showMessageDialog(studentInformationFrame, "Data submitted successfully");
            
            } else if (e.getSource() == updateButton) {
            updateData();
            JOptionPane.showMessageDialog(studentInformationFrame, "Data updated successfully");
            
        } else if (e.getSource() == searchBar) {
            String keyword = searchBar.getText().trim();
            searchInDatabase(keyword);
        }
    }

}
