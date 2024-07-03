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
    JButton searchButton, addButton, submitButton, deleteButton, viewButton, backButton;
    JTextField searchBar;
    

    Connection conn;
    String dbUrl = "jdbc:mysql://localhost:3306/user_login";
    String dbUser = "root";
    String dbPassword ="rootmjv_root16";

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
        headerLabel.setForeground(new Color(245, 245, 220));
        headerPanel.add(headerLabel);

                model = new DefaultTableModel(new Object[]{"Name", "Course", "Subject", "Date", "Attendance"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true; 
            }
        };
                
         

        table = new JTable(model);
        table.setOpaque(false);
        table.setBackground(new Color(245, 245, 220));

        tableHeader = table.getTableHeader();
        tableHeader.setBackground(new Color(128, 0, 0));
        tableHeader.setForeground(new Color(245, 245, 220));
        tableHeader.setFont(new Font("Arial Black", Font.BOLD, 13));

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(45, 160, 1350, 500);

        tableBorderColor = new Color(128, 0, 0);
        scrollPane.setBorder(new LineBorder(tableBorderColor, 8));

        searchBar = new JTextField(20);
        searchBar.setBounds(50,120,300,30);
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
        
        viewButton = new JButton("View Records");
        viewButton.setFont(new Font("Arial Black", Font.BOLD, 13));
        viewButton.setForeground(new Color(128, 0, 0));
        viewButton.setBackground(new Color(245, 245, 220));
        viewButton.setOpaque(true);
        viewButton.setBorder(null);
        viewButton.setBounds(270, 680, 200, 50);
        viewButton.addActionListener(this);

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
        attendanceFrame.add(viewButton);
        attendanceFrame.add(deleteButton);
        attendanceFrame.add(backButton);
        attendanceFrame.add(panelBG);
        

        attendanceFrame.pack();
        attendanceFrame.setVisible(true);

        try {
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            System.out.println("Connected to the database");
        } catch (SQLException ex) {
            System.out.println("Failed to connect to the database");
            ex.printStackTrace();
        }
    }

  
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
       if (e.getSource() == searchButton) {
            String keyword = searchBar.getText();
                searchRecord(keyword);
        }else if(e.getSource() == addButton) {
                addRow(); 
        }else if(e.getSource() == submitButton) {
            if (checkEmptyFields()) {
                JOptionPane.showMessageDialog(attendanceFrame, "Please complete all fields before saving.", "Unable to Submit Record", JOptionPane.WARNING_MESSAGE);
            }else {
                saveToDatabase();
            }    
        }else if (e.getSource() == viewButton) {
            viewRecords();
        }else if (e.getSource() == deleteButton) {
            deleteRecord();
        }else if (e.getSource() == backButton) {
            new Dashboard();
        }
    }
    
    

private void addRow() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(new Date());
        model.addRow(new Object[]{"", "", "", currentDate, ""});
    } 


private boolean checkEmptyFields() {
        int rowCount = model.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            String name = (String) model.getValueAt(i, 0);
            String course = (String) model.getValueAt(i, 1);
            String subject = (String) model.getValueAt(i, 2);
            String attendance = (String) model.getValueAt(i, 4);

            if (name.isEmpty() || course.isEmpty() || subject.isEmpty() || attendance.isEmpty()) {
                return true; 
            }
        }
        return false;
    }

    private void saveToDatabase() {
    String checkQuery = "SELECT COUNT(*) FROM attendance_record WHERE name = ? AND course = ? AND subject = ? AND date = ? AND attendance = ?";
    String insertQuery = "INSERT INTO attendance_record (name, course, subject, date, attendance) VALUES (?, ?, ?, ?, ?)";
    try (
        PreparedStatement checkPs = conn.prepareStatement(checkQuery);
        PreparedStatement insertPs = conn.prepareStatement(insertQuery)
    ) {
        int rowCount = model.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            String name = (String) model.getValueAt(i, 0);
            String course = (String) model.getValueAt(i, 1);
            String subject = (String) model.getValueAt(i, 2);
            String date = (String) model.getValueAt(i, 3);
            String attendance = (String) model.getValueAt(i, 4);

            if (!name.isEmpty() && !course.isEmpty() && !subject.isEmpty() && !attendance.isEmpty()) {
              
                checkPs.setString(1, name);
                checkPs.setString(2, course);
                checkPs.setString(3, subject);
                checkPs.setString(4, date);
                checkPs.setString(5, attendance);
                
                ResultSet rs = checkPs.executeQuery();
                rs.next();
                int count = rs.getInt(1);
                
                if (count == 0) {
                   
                    insertPs.setString(1, name);
                    insertPs.setString(2, course);
                    insertPs.setString(3, subject);
                    insertPs.setString(4, date);
                    insertPs.setString(5, attendance);
                    insertPs.addBatch();
                }
            }
        }
        insertPs.executeBatch();
        JOptionPane.showMessageDialog(attendanceFrame, "Records saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        
        
        model.setRowCount(0);

       
        viewRecords();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(attendanceFrame, "Error saving records: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    }
}


     private void searchRecord(String keyword) {
        model.setRowCount(0);
        String query = "SELECT * FROM attendance_record WHERE name LIKE ? OR course LIKE ? OR subject LIKE ? OR date LIKE ? OR attendance LIKE ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            String likeKeyword = "%" + keyword + "%";
            pstmt.setString(1, likeKeyword);
            pstmt.setString(2, likeKeyword);
            pstmt.setString(3, likeKeyword);
            pstmt.setString(4, likeKeyword);
            pstmt.setString(5, likeKeyword);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("name"),
                    rs.getString("course"),
                    rs.getString("subject"),
                    rs.getString("date"),
                    rs.getString("attendance"),
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   private void viewRecords() {
 
        try {
            String query = "SELECT * FROM attendance_record";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            model.setRowCount(0); 
            while (rs.next()) {
                String name = rs.getString("name");
                String course = rs.getString("course");
                String subject = rs.getString("subject");
                Date date = rs.getDate("date");
                String attendance = rs.getString("attendance");

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = sdf.format(date);

                model.addRow(new Object[]{name, course, subject, formattedDate, attendance});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(attendanceFrame, "Error fetching records: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

   
   
   
   
   
 private void deleteRecord() {
    int selectedRow = table.getSelectedRow();
    if (selectedRow != -1) {
        String name = (String) model.getValueAt(selectedRow, 0); 
        String course = (String) model.getValueAt(selectedRow, 1);
        String subject = (String) model.getValueAt(selectedRow, 2);
        String date = (String) model.getValueAt(selectedRow, 3);
        String attendance = (String) model.getValueAt(selectedRow, 4);

        try {
            String deleteQuery = "DELETE FROM attendance_record WHERE name = ? AND course = ? AND subject = ? AND date = ? AND attendance = ?";
            PreparedStatement ps = conn.prepareStatement(deleteQuery);
            ps.setString(1, name);
            ps.setString(2, course);
            ps.setString(3, subject);
            ps.setString(4, date);
            ps.setString(5, attendance);
            int rowsDeleted = ps.executeUpdate();
            
            if (rowsDeleted > 0) {
                model.removeRow(selectedRow);
                JOptionPane.showMessageDialog(attendanceFrame, "Record deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(attendanceFrame, "Error deleting record.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(attendanceFrame, "Error deleting record: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    } else {
        JOptionPane.showMessageDialog(attendanceFrame, "No record selected for deletion.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

 
}