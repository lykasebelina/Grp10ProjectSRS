package srsproject;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.border.LineBorder;
import javax.swing.table.*;

public class AttendanceRecord extends JFrame implements ActionListener {

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
    JButton searchButton, createButton, addButton, submitButton, removeButton, viewButton, editButton, updateButton, backButton;
    JTextField searchBar;

    Connection conn;
    String dbUrl = "jdbc:mysql://localhost:3306/user_login";
    String dbUser = "root";
    String dbPassword ="rootmjv_root16";

    public AttendanceRecord() {
        attendanceFrame = new JFrame();
        attendanceFrame.setTitle("Student Attendance");
        attendanceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        attendanceFrame.setSize(1500, 1000);
        
        

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
        searchPanel = new JPanel(new BorderLayout());
        searchPanel.add(searchBar);
        searchPanel.setBounds(50, 120, 300, 30);
        
        
   
           
        createButton = new JButton("Create New Record");
        createButton.setFont(new Font("Arial Black", Font.BOLD, 13));
        createButton.setForeground(new Color(128, 0, 0));
        createButton.setBackground(new Color(245, 245, 220));
        createButton.setOpaque(true);
        createButton.setBorder(null);
        createButton.setBounds(50, 680, 200, 50);
        createButton.addActionListener(this);
       
        
        

        addButton = new JButton("Add Row");
        addButton.setFont(new Font("Arial Black", Font.BOLD, 13));
        addButton.setForeground(new Color(128, 0, 0));
        addButton.setBackground(new Color(245, 245, 220));
        addButton.setOpaque(true);
        addButton.setBorder(null);
        addButton.setBounds(50, 750, 200, 50);
        addButton.addActionListener(this);

        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial Black", Font.BOLD, 13));
        submitButton.setForeground(new Color(128, 0, 0));
        submitButton.setBackground(new Color(245, 245, 220));
        submitButton.setOpaque(true);
        submitButton.setBorder(null);
        submitButton.setBounds(270, 680, 200, 50);
        submitButton.addActionListener(this);

        removeButton = new JButton("Remove Row");
        removeButton.setFont(new Font("Arial Black", Font.BOLD, 13));
        removeButton.setForeground(new Color(128, 0, 0));
        removeButton.setBackground(new Color(245, 245, 220));
        removeButton.setOpaque(true);
        removeButton.setBorder(null);
        removeButton.setBounds(490, 680, 200, 50);
        removeButton.addActionListener(this);

        viewButton = new JButton("View Records");
        viewButton.setFont(new Font("Arial Black", Font.BOLD, 13));
        viewButton.setForeground(new Color(128, 0, 0));
        viewButton.setBackground(new Color(245, 245, 220));
        viewButton.setOpaque(true);
        viewButton.setBorder(null);
        viewButton.setBounds(710, 680, 200, 50);
        viewButton.addActionListener(this);
        
        editButton = new JButton("Edit Records");
        editButton.setFont(new Font("Arial Black", Font.BOLD, 13));
        editButton.setForeground(new Color(128, 0, 0));
        editButton.setBackground(new Color(245, 245, 220));
        editButton.setOpaque(true);
        editButton.setBorder(null);
        editButton.setBounds(930, 680, 200, 50);
        editButton.addActionListener(this);

        updateButton = new JButton("Update Records");
        updateButton.setFont(new Font("Arial Black", Font.BOLD, 13));
        updateButton.setForeground(new Color(128, 0, 0));
        updateButton.setBackground(new Color(245, 245, 220));
        updateButton.setOpaque(true);
        updateButton.setBorder(null);
        updateButton.setBounds(1150, 680, 200, 50);
        updateButton.addActionListener(this);

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
        attendanceFrame.add(createButton);
        attendanceFrame.add(addButton);
        attendanceFrame.add(submitButton);
        attendanceFrame.add(removeButton);
        attendanceFrame.add(viewButton);
        attendanceFrame.add(editButton);
        attendanceFrame.add(updateButton);
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
        
        if(e.getSource() == createButton) {
                
               new AttendanceRecord();
                
        }else if(e.getSource() == addButton) {
                addRow(); 
        }else if(e.getSource() == submitButton) {
            if (checkEmptyFields()) {
                JOptionPane.showMessageDialog(this, "Please complete all fields before saving.", "Unable to Submit Record", JOptionPane.WARNING_MESSAGE);
            } else {
                saveToDatabase();
            }
        } else if (e.getSource() == removeButton) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) { // Ensure a row is selected
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to delete.", "No Row Selected", JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (e.getSource() == searchButton) {
     
            String keyword = searchBar.getText();
                searchRecord(keyword);
        } else if (e.getSource() == viewButton) {
            viewRecords();
        } else if (e.getSource() == editButton) {
            
        } else if (e.getSource() == updateButton) {
            
        }else if (e.getSource() == backButton) {
            attendanceFrame.dispose();
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
                return true; // Return true if any required field is empty
            }
        }
        return false;
    }

    private void saveToDatabase() {
        String insertQuery = "INSERT INTO attendance_record (name, course, subject, date, attendance) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(insertQuery)) {
            int rowCount = model.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                String name = (String) model.getValueAt(i, 0);
                String course = (String) model.getValueAt(i, 1);
                String subject = (String) model.getValueAt(i, 2);
                String date = (String) model.getValueAt(i, 3);
                String attendance = (String) model.getValueAt(i, 4);

                if (!name.isEmpty() && !course.isEmpty() && !subject.isEmpty() && !attendance.isEmpty()) { // Only save rows with all required fields
                    ps.setString(1, name);
                    ps.setString(2, course);
                    ps.setString(3, subject);
                    ps.setString(4, date);
                    ps.setString(5, attendance);
                    ps.addBatch();
                }
            }
            ps.executeBatch();
            JOptionPane.showMessageDialog(this, "Records saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            viewRecords();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error saving records: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }


      private void searchRecord(String keyword) {
        model.setRowCount(0);
        String query = "SELECT * FROM attendance_record WHERE name LIKE ? OR student_id LIKE ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
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
        String query = "SELECT * FROM attendance_record ORDER BY date DESC, course ASC, name ASC";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        DefaultTableModel newModel = new DefaultTableModel(new Object[]{"Name", "Course", "Subject", "Date", "Attendance"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable for viewing
            }
        };

        while (rs.next()) {
            String name = rs.getString("name");
            String course = rs.getString("course");
            String subject = rs.getString("subject");
            Date date = rs.getDate("date");
            String attendance = rs.getString("attendance");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = sdf.format(date);

            newModel.addRow(new Object[]{name, course, subject, formattedDate, attendance});
        }

        table.setModel(newModel); // Set the updated model to the JTable

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error fetching records: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    }
}

    
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AttendanceRecord());
    }
}