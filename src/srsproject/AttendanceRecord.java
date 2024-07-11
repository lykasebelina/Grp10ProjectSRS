package srsproject;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.border.LineBorder;
import javax.swing.table.*;

public class AttendanceRecord implements ActionListener {

    JFrame attendanceFrame;
    Image background;
    ImageIcon bckgrnd, bgImage;
    JPanel headerPanel, panelBG, searchPanel;
    JLabel headerLabel, imageLabel;
    JTable table;
    Color tableBorderColor;
    JTableHeader tableHeader;
    JScrollPane scrollPane;
    DefaultTableModel model;
    JButton searchButton, addButton, submitButton, deleteButton, refreshButton, backButton;
    JTextField searchBar;
    private int selectedRow = -1;

    Connection conn;
    String dbUrl = "jdbc:mysql://localhost:3306/studrec_mgmt";
    String dbUser = "root";
    String dbPassword = "";

    public AttendanceRecord() {
        attendanceFrame = new JFrame();
        attendanceFrame.setTitle("Student Attendance");

        attendanceFrame.setSize(1500, 1000);
        attendanceFrame.setLocationRelativeTo(null);

        attendanceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        headerPanel = new JPanel();
        headerPanel.setBackground(new Color(128, 0, 0));
        headerPanel.setBounds(0, 0, 1500, 100);

        headerLabel = new JLabel("BSIT STUDENT RECORD SYSTEM");
        headerLabel.setPreferredSize(new Dimension(720, 100));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 38));
        headerPanel.add(headerLabel);

        model = new DefaultTableModel(new Object[]{"Student ID", "Name", "Course", "Subject", "Date", "Attendance"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Allow editing for Subject (index 3), Date (index 4), and Attendance (index 5) columns
                return column == 3 || column == 4 || column == 5;
            }
        };

        table = new JTable(model);
        table.setOpaque(false);
        table.setBackground(new Color(245, 245, 220));
        table.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        selectedRow = table.getSelectedRow();
    }
});

        tableHeader = table.getTableHeader();
        tableHeader.setBackground(new Color(128, 0, 0));
        tableHeader.setForeground(new Color(245, 245, 220));
        tableHeader.setFont(new Font("Arial Black", Font.BOLD, 13));

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(45, 160, 1350, 500);

        tableBorderColor = new Color(128, 0, 0);
        scrollPane.setBorder(new LineBorder(tableBorderColor, 8));

        String[] attendance = {"Present", "Absent"};
        JComboBox<String> Attendance = new JComboBox<>(attendance);
        table.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(Attendance));

        String[] subjects = {"COMP090", "COMP010", "COMP012", "COMP013", "COMP014", "ELECIT_FE2", "INTE202", "PATHFIT4"};
        JComboBox<String> Subject = new JComboBox<>(subjects);
        table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(Subject));

        searchBar = new JTextField(20);
        searchBar.setBounds(50, 120, 300, 30);
        searchPanel = new JPanel(new BorderLayout());
        searchPanel.add(searchBar);
        searchPanel.setBounds(50, 120, 400, 30);

        searchButton = new JButton("SEARCH");
        searchButton.setFont(new Font("Arial Black", Font.BOLD, 13));
        searchButton.setForeground(new Color(128, 0, 0));
        searchButton.setBackground(new Color(245, 245, 220));
        searchButton.setOpaque(true);
        searchButton.setBorder(null);
        searchButton.setBounds(460, 120, 95, 26);
        searchButton.addActionListener(this);

        addButton = new JButton("Add New Records");
        addButton.setFont(new Font("Arial Black", Font.BOLD, 13));
        addButton.setForeground(new Color(128, 0, 0));
        addButton.setBackground(new Color(245, 245, 220));
        addButton.setOpaque(true);
        addButton.setBorder(null);
        addButton.setBounds(50, 680, 200, 50);
        addButton.addActionListener(this);

        refreshButton = new JButton("View Records");
        refreshButton.setFont(new Font("Arial Black", Font.BOLD, 13));
        refreshButton.setForeground(new Color(128, 0, 0));
        refreshButton.setBackground(new Color(245, 245, 220));
        refreshButton.setOpaque(true);
        refreshButton.setBorder(null);
        refreshButton.setBounds(270, 680, 200, 50);
        refreshButton.addActionListener(this);

        submitButton = new JButton("Submit Records");
        submitButton.setFont(new Font("Arial Black", Font.BOLD, 13));
        submitButton.setForeground(new Color(128, 0, 0));
        submitButton.setBackground(new Color(245, 245, 220));
        submitButton.setOpaque(true);
        submitButton.setBorder(null);
        submitButton.setBounds(490, 680, 200, 50);
        submitButton.addActionListener(this);

        deleteButton = new JButton("Delete Records");
        deleteButton.setFont(new Font("Arial Black", Font.BOLD, 13));
        deleteButton.setForeground(new Color(128, 0, 0));
        deleteButton.setBackground(new Color(245, 245, 220));
        deleteButton.setOpaque(true);
        deleteButton.setBorder(null);
        deleteButton.setBounds(710, 680, 200, 50);
        deleteButton.addActionListener(this);

        backButton = new JButton("BACK");
        backButton.setFont(new Font("Arial Black", Font.BOLD, 13));
        backButton.setForeground(new Color(128, 0, 0));
        backButton.setBackground(new Color(245, 245, 220));
        backButton.setOpaque(true);
        backButton.setBorder(null);
        backButton.setBounds(1180, 720, 200, 50);
        backButton.addActionListener(this);

        bgImage = new ImageIcon("folderimage/sbBinan.jpg");
        background = bgImage.getImage().getScaledInstance(1500, 1000, Image.SCALE_SMOOTH);
        bckgrnd = new ImageIcon(background);

        imageLabel = new JLabel(bckgrnd);

        panelBG = new JPanel();
        panelBG.setBounds(0, -10, 1500, 1000);
        panelBG.add(imageLabel);

        attendanceFrame.add(scrollPane);
        attendanceFrame.add(headerPanel);
        attendanceFrame.add(searchPanel);
        attendanceFrame.add(searchButton);
        attendanceFrame.add(addButton);
        attendanceFrame.add(submitButton);
        attendanceFrame.add(refreshButton);
        attendanceFrame.add(deleteButton);
        attendanceFrame.add(backButton);
        attendanceFrame.add(panelBG);

        attendanceFrame.pack();
        attendanceFrame.setVisible(true);

        try {
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            System.out.println("Connected to the database");
            loadData();
        } catch (SQLException ex) {
            System.out.println("Failed to connect to the database");
            ex.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            searchRecord();
        } else if (e.getSource() == addButton) {
            addRow();
        } else if (e.getSource() == submitButton) {
            saveRecords();
        } else if (e.getSource() == refreshButton) {
                  refreshData();
        } else if (e.getSource() == backButton) {
            Dashboard db = new Dashboard();
        }else if (e.getSource() == deleteButton) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            // Get student_id from the selected row
            String studentId = (String) model.getValueAt(selectedRow, 0);
            // Call method to delete the record based on student_id
            deleteRecord(studentId);
        } else {
            // Show warning if no row is selected
            JOptionPane.showMessageDialog(attendanceFrame, "No row selected.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
    // Handle other action events here...
}
    
    private void refreshData() {
    try {
        model.setRowCount(0); // Clear the table before adding new data

        String query = "SELECT si.student_id, si.name, si.course, ar.subject, ar.date, ar.attendance_status " +
                       "FROM student_info si " +
                       "LEFT JOIN attendance_record ar ON si.student_id = ar.student_id " +
                       "WHERE si.student_id IS NOT NULL AND si.name IS NOT NULL " +
                       "AND ar.subject IS NOT NULL AND ar.date IS NOT NULL AND ar.attendance_status IS NOT NULL";

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            String studentId = rs.getString("student_id");
            String name = rs.getString("name");
            String course = rs.getString("course");
            String subject = rs.getString("subject");
            String date = rs.getString("date");
            String attendanceStatus = rs.getString("attendance_status");

            // Check if any field is null or empty before adding to model
            if (studentId != null && name != null && course != null && subject != null && date != null && attendanceStatus != null &&
                !studentId.isEmpty() && !name.isEmpty() && !course.isEmpty() && !subject.isEmpty() && !date.isEmpty() && !attendanceStatus.isEmpty()) {
                model.addRow(new Object[]{studentId, name, course, subject, date, attendanceStatus});
            }
        }

        stmt.close();
        rs.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(attendanceFrame, "Failed to refresh data.");
    }
}

    
    

    private void addRow() {
        try {
            model.setRowCount(0);

            String query = "SELECT student_id, name, course FROM student_info";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String studentId = rs.getString("student_id");
                String name = rs.getString("name");
                String course = rs.getString("course");

               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
               String currentDate = sdf.format(new Date());

                model.addRow(new Object[]{studentId, name, course, "", currentDate, ""});
            }

            stmt.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void saveRecords() {
        if (selectedRow != -1) {
        
        try {
            String studentId = (String) model.getValueAt(selectedRow, 0);
            String subject = (String) model.getValueAt(selectedRow, 3);
            String date = (String) model.getValueAt(selectedRow, 4);
            String attendance_status = (String) model.getValueAt(selectedRow, 5);
            

            // Check if student_id already exists in grade_system table
            String checkQuery = "SELECT * FROM attendance_record WHERE student_id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setString(1, studentId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                // Student record already exists, show error message
                JOptionPane.showMessageDialog(attendanceFrame, "Please click on 'Update Records' to save changes.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Insert new record
                String insertQuery = "INSERT INTO attendance_record (student_id, subject, date, attendance_status) " +
                                     "VALUES (?, ?, ?, ?)";
                PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
                insertStmt.setString(1, studentId);
                insertStmt.setString(2, subject);
                insertStmt.setString(3, date);
                insertStmt.setString(4, attendance_status); 

                insertStmt.executeUpdate();
                insertStmt.close();

                JOptionPane.showMessageDialog(attendanceFrame, "Record saved successfully!");
            }

            checkStmt.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(attendanceFrame, "Failed to save record.");
        }
    } else {
        JOptionPane.showMessageDialog(attendanceFrame, "No row selected.");
    }
}

   


    private void loadData() {
    try {
        model.setRowCount(0); // Clear the table before adding new data

        String query = "SELECT si.student_id, si.name, si.course, ar.subject, ar.date, ar.attendance_status " +
                       "FROM student_info si " +
                       "LEFT JOIN attendance_record ar ON si.student_id = ar.student_id " +
                       "WHERE si.student_id IS NOT NULL AND si.name IS NOT NULL " +
                       "AND ar.subject IS NOT NULL AND ar.date IS NOT NULL AND ar.attendance_status IS NOT NULL";

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            String studentId = rs.getString("student_id");
            String name = rs.getString("name");
            String course = rs.getString("course");
            String subject = rs.getString("subject");
            String date = rs.getString("date");
            String attendanceStatus = rs.getString("attendance_status");

            model.addRow(new Object[]{studentId, name, course, subject, date, attendanceStatus});
        }

        stmt.close();
        rs.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}


    

private void searchRecord() {
    String searchText = searchBar.getText().trim();
    
    try {
        model.setRowCount(0); // Clear existing table data
        
        // SQL query to fetch complete records based on search criteria
        String query = "SELECT si.student_id, si.name, si.course, ar.subject, ar.date, ar.attendance_status " +
                       "FROM student_info si LEFT JOIN attendance_record ar ON si.student_id = ar.student_id " +
                       "WHERE (si.student_id LIKE ? OR si.name LIKE ? OR ar.date LIKE ? OR ar.subject LIKE ?) " +
                       "AND si.student_id IS NOT NULL AND si.name IS NOT NULL " +
                       "AND ar.subject IS NOT NULL AND ar.date IS NOT NULL";
        
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, "%" + searchText + "%"); // Search by student_id
        pstmt.setString(2, "%" + searchText + "%"); // Search by name
        pstmt.setString(3, "%" + searchText + "%"); // Search by date
        pstmt.setString(4, "%" + searchText + "%"); // Search by subject
        
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            String studentId = rs.getString("student_id");
            String name = rs.getString("name");
            String course = rs.getString("course");
            String subject = rs.getString("subject");
            String date = rs.getString("date");
            String attendanceStatus = rs.getString("attendance_status");
            
            // Check if any field is null or empty before adding to model
            if (studentId != null && name != null && subject != null && date != null && attendanceStatus != null &&
                !studentId.isEmpty() && !name.isEmpty() && !subject.isEmpty() && !date.isEmpty() && !attendanceStatus.isEmpty()) {
                model.addRow(new Object[]{studentId, name, course, subject, date, attendanceStatus});
            }
        }

        pstmt.close();
        rs.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(attendanceFrame, "Failed to search records.");
    }
}




         private void deleteRecord(String studentId) {
    // Check if studentId is null or empty
    if (studentId == null || studentId.isEmpty()) {
        JOptionPane.showMessageDialog(attendanceFrame, "No row selected.", "Warning", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // SQL query to delete record from attendance_record table
    String query = "DELETE FROM attendance_record WHERE student_id = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
        // Set student_id parameter
        pstmt.setString(1, studentId);
        
        // Execute delete query
        int rowsAffected = pstmt.executeUpdate();

        // Check if deletion was successful
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(attendanceFrame, "Record deleted successfully.");
            // Refresh table after deletion
            loadData();
        } else {
            JOptionPane.showMessageDialog(attendanceFrame, "Failed to delete record.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(attendanceFrame, "Failed to delete record.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}



    
    public static void main(String[] args) {
        AttendanceRecord db = new AttendanceRecord();
    }
}