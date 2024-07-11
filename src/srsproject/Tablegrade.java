package srsproject;



import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.LineBorder;
import javax.swing.table.*;




public class Tablegrade implements ActionListener {

    
    
    
    JFrame gradeFrame;
    Image background;
    ImageIcon bckgrnd, bgImage;
    JPanel headerPanel, panelBG, searchPanel;
    JLabel headerLabel, imageLabel;
    JTable table;
    Color tableBorderColor;
    JTableHeader tableHeader;
    JScrollPane scrollPane;
    DefaultTableModel model;
    JButton searchButton, submitButton, deleteButton, updateButton, backButton, refreshButton;
    JTextField searchBar;
    JButton computeButton;
    private int selectedRow = -1;

    Connection conn;
    String dbUrl = "jdbc:mysql://localhost:3306/user_login";
    String dbUser = "root";
    String dbPassword ="rootmjv_root16";

    public Tablegrade() {
        gradeFrame = new JFrame();
        gradeFrame.setTitle("Student Grades");
        
        gradeFrame.setSize(1500, 1000);
        gradeFrame.setLocationRelativeTo(null);
        
        gradeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        headerPanel = new JPanel();
        headerPanel.setBackground(new Color(128, 0, 0));
        headerPanel.setBounds(0, 0, 1500, 100);

        headerLabel = new JLabel("BSIT STUDENT RECORD SYSTEM");
        headerLabel.setPreferredSize(new Dimension(720, 100));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 38));
        headerPanel.add(headerLabel);

               model = new DefaultTableModel(new Object[]{"Student ID", "Name", "Course", "COMP090", "COMP010", "COMP012", "COMP013", "COMP014", "ELECIT_FE2", "INTE202", "PATHFIT4", "GWA"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column >= 3 && column <= 11;
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
           

       
        
        updateButton = new JButton("Update Records");
        updateButton.setFont(new Font("Arial Black", Font.BOLD, 13));
        updateButton.setForeground(new Color(128, 0, 0));
        updateButton.setBackground(new Color(245, 245, 220));
        updateButton.setOpaque(true);
        updateButton.setBorder(null);
        updateButton.setBounds(490, 680, 200, 50);
        updateButton.addActionListener(this);

        submitButton = new JButton("Submit Records");
        submitButton.setFont(new Font("Arial Black", Font.BOLD, 13));
        submitButton.setForeground(new Color(128, 0, 0));
        submitButton.setBackground(new Color(245, 245, 220));
        submitButton.setOpaque(true);
        submitButton.setBorder(null);
        submitButton.setBounds(270, 680, 200, 50);
        submitButton.addActionListener(this);


    computeButton = new JButton("Compute Average");
    computeButton.setFont(new Font("Arial Black", Font.BOLD, 13));
    computeButton.setForeground(new Color(128, 0, 0));
    computeButton.setBackground(new Color(245, 245, 220));
    computeButton.setOpaque(true);
    computeButton.setBorder(null);
    computeButton.setBounds(50, 680, 200, 50);
    computeButton.addActionListener(this);
        
        
        deleteButton = new JButton("Delete Records");
        deleteButton.setFont(new Font("Arial Black", Font.BOLD, 13));
        deleteButton.setForeground(new Color(128, 0, 0));
        deleteButton.setBackground(new Color(245, 245, 220));
        deleteButton.setOpaque(true);
        deleteButton.setBorder(null);
        deleteButton.setBounds(710, 680, 200, 50);
        deleteButton.addActionListener(this);
        
        
        refreshButton = new JButton("Refresh");
        refreshButton.setFont(new Font("Arial Black", Font.BOLD, 13));
        refreshButton.setForeground(new Color(128, 0, 0));
        refreshButton.setBackground(new Color(245, 245, 220));
        refreshButton.setOpaque(true);
        refreshButton.setBorder(null);
        refreshButton.setBounds(930, 680, 200, 50);
        refreshButton.addActionListener(this);



    

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

        gradeFrame.add(scrollPane);
        gradeFrame.add(headerPanel);
        gradeFrame.add(searchPanel); 
        gradeFrame.add(searchButton);
        gradeFrame.add(computeButton);
        gradeFrame.add(submitButton);
        gradeFrame.add(updateButton);
        gradeFrame.add(deleteButton);
        gradeFrame.add(refreshButton);
        gradeFrame.add(backButton);
        gradeFrame.add(panelBG);
        

        gradeFrame.pack();
        gradeFrame.setVisible(true);

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

        }else if(e.getSource() == submitButton) {
            saveRecords();
        }else if (e.getSource() == updateButton) {
            updateData();
        }else if (e.getSource() == backButton) {
            gradeFrame.dispose();
            Dashboard db = new Dashboard(); 
        }
        else if (e.getSource() == computeButton) {
        computeAverage();
        }else if (e.getSource() == deleteButton) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String studentId = (String) model.getValueAt(selectedRow, 0);
                deleteRowFromDatabase(studentId);
                model.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(gradeFrame, "Select a row to delete", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else if (e.getSource() == refreshButton) {
        refreshTable();
    }
    }
    
    
    private void refreshTable() {
        loadData();
}
    
    
    
private void loadData() {
    try {
        model.setRowCount(0);

        String query = "SELECT si.student_id, si.name, si.course, gs.comp090, gs.comp010, gs.comp012, gs.comp013, gs.comp014, gs.elecit_fe2, gs.inte202, gs.pathfit4, gs.gwa " +
                       "FROM student_info si LEFT JOIN grade_system gs ON si.student_id = gs.student_id";
        
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            String studentId = rs.getString("student_id");
            String name = rs.getString("name");
            String course = rs.getString("course");
            String comp090 = rs.getString("comp090");
            String comp010 = rs.getString("comp010");
            String comp012 = rs.getString("comp012");
            String comp013 = rs.getString("comp013");
            String comp014 = rs.getString("comp014");
            String elecit_fe2 = rs.getString("elecit_fe2");
            String inte202 = rs.getString("inte202");
            String pathfit4 = rs.getString("pathfit4");
            String gwa = rs.getString("gwa");

            model.addRow(new Object[]{studentId, name, course, comp090, comp010, comp012, comp013, comp014, elecit_fe2, inte202, pathfit4, gwa});
        }

        stmt.close();
        rs.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}

      
         private void deleteRowFromDatabase(String studentId) {
        String query = "DELETE FROM grade_system WHERE student_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, studentId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

         
   private void searchRecord() {
    String searchText = searchBar.getText().trim();
    
    try {
        model.setRowCount(0); 
        
    
        String query = "SELECT si.student_id, si.name, si.course, gs.comp090, gs.comp010, gs.comp012, gs.comp013, gs.comp014, gs.elecit_fe2, gs.inte202, gs.pathfit4, gs.gwa " +
                       "FROM student_info si LEFT JOIN grade_system gs ON si.student_id = gs.student_id " +
                       "WHERE si.student_id LIKE ? OR si.name LIKE ? OR si.course LIKE ? OR gs.gwa LIKE ?";
        
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, "%" + searchText + "%"); 
        pstmt.setString(2, "%" + searchText + "%");
        pstmt.setString(3, "%" + searchText + "%"); 
        pstmt.setString(4, "%" + searchText + "%");
        
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            String studentId = rs.getString("student_id");
            String name = rs.getString("name");
            String course = rs.getString("course");
            String comp090 = rs.getString("comp090");
            String comp010 = rs.getString("comp010");
            String comp012 = rs.getString("comp012");
            String comp013 = rs.getString("comp013");
            String comp014 = rs.getString("comp014");
            String elecit_fe2 = rs.getString("elecit_fe2");
            String inte202 = rs.getString("inte202");
            String pathfit4 = rs.getString("pathfit4");
            String gwa = rs.getString("gwa");

            model.addRow(new Object[]{studentId, name, course, comp090, comp010, comp012, comp013, comp014, elecit_fe2, inte202, pathfit4, gwa});
        }

        pstmt.close();
        rs.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(gradeFrame, "Failed to search records.");
    }
}



private void saveRecords() {
    if (selectedRow != -1) { 
        try {
            String studentId = (String) model.getValueAt(selectedRow, 0);
            String comp090 = (String) model.getValueAt(selectedRow, 3);
            String comp010 = (String) model.getValueAt(selectedRow, 4);
            String comp012 = (String) model.getValueAt(selectedRow, 5);
            String comp013 = (String) model.getValueAt(selectedRow, 6);
            String comp014 = (String) model.getValueAt(selectedRow, 7);
            String elecit_fe2 = (String) model.getValueAt(selectedRow, 8);
            String inte202 = (String) model.getValueAt(selectedRow, 9);
            String pathfit4 = (String) model.getValueAt(selectedRow, 10);
            String gwa = (String) model.getValueAt(selectedRow, 11);

       
            String checkQuery = "SELECT * FROM grade_system WHERE student_id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setString(1, studentId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
              
                JOptionPane.showMessageDialog(gradeFrame, "Please click on 'Update Records' to save changes.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
               
                String insertQuery = "INSERT INTO grade_system (student_id, comp090, comp010, comp012, comp013, comp014, elecit_fe2, inte202, pathfit4, gwa) " +
                                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
                insertStmt.setString(1, studentId);
                insertStmt.setString(2, comp090);
                insertStmt.setString(3, comp010);
                insertStmt.setString(4, comp012);
                insertStmt.setString(5, comp013);
                insertStmt.setString(6, comp014);
                insertStmt.setString(7, elecit_fe2);
                insertStmt.setString(8, inte202);
                insertStmt.setString(9, pathfit4);
                insertStmt.setString(10, gwa);

                insertStmt.executeUpdate();
                insertStmt.close();

                JOptionPane.showMessageDialog(gradeFrame, "Record saved successfully!");
            }

            checkStmt.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(gradeFrame, "Failed to save record.");
        }
    } else {
        JOptionPane.showMessageDialog(gradeFrame, "No row selected.");
    }
}




   private void updateData() {
    try {
        boolean changesMade = false;

        for (int row = 0; row < model.getRowCount(); row++) {
            String studentId = (String) model.getValueAt(row, 0);
            String comp090 = (String) model.getValueAt(row, 3);
            String comp010 = (String) model.getValueAt(row, 4);
            String comp012 = (String) model.getValueAt(row, 5);
            String comp013 = (String) model.getValueAt(row, 6);
            String comp014 = (String) model.getValueAt(row, 7);
            String elecit_fe2 = (String) model.getValueAt(row, 8);
            String inte202 = (String) model.getValueAt(row, 9);
            String pathfit4 = (String) model.getValueAt(row, 10);
            String gwa = (String) model.getValueAt(row, 11);

       
            String query = "SELECT comp090, comp010, comp012, comp013, comp014, elecit_fe2, inte202, pathfit4, gwa FROM grade_system WHERE student_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String originalComp090 = rs.getString("comp090");
                String originalComp010 = rs.getString("comp010");
                String originalComp012 = rs.getString("comp012");
                String originalComp013 = rs.getString("comp013");
                String originalComp014 = rs.getString("comp014");
                String originalElecitFe2 = rs.getString("elecit_fe2");
                String originalInte202 = rs.getString("inte202");
                String originalPathfit4 = rs.getString("pathfit4");
                String originalGwa = rs.getString("gwa");

            
                if (!comp090.equals(originalComp090) || !comp010.equals(originalComp010) || !comp012.equals(originalComp012) ||
                    !comp013.equals(originalComp013) || !comp014.equals(originalComp014) || !elecit_fe2.equals(originalElecitFe2) ||
                    !inte202.equals(originalInte202) || !pathfit4.equals(originalPathfit4) || !gwa.equals(originalGwa)) {
                    
                 
                    String updateQuery = "UPDATE grade_system SET comp090 = ?, comp010 = ?, comp012 = ?, comp013 = ?, comp014 = ?, elecit_fe2 = ?, inte202 = ?, pathfit4 = ?, gwa = ? WHERE student_id = ?";
                    PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
                    updateStmt.setString(1, comp090);
                    updateStmt.setString(2, comp010);
                    updateStmt.setString(3, comp012);
                    updateStmt.setString(4, comp013);
                    updateStmt.setString(5, comp014);
                    updateStmt.setString(6, elecit_fe2);
                    updateStmt.setString(7, inte202);
                    updateStmt.setString(8, pathfit4);
                    updateStmt.setString(9, gwa);
                    updateStmt.setString(10, studentId);

                    updateStmt.executeUpdate();
                    updateStmt.close();
                    changesMade = true;
                }
            }

            stmt.close();
            rs.close();
        }

        if (changesMade) {
            JOptionPane.showMessageDialog(gradeFrame, "Records updated successfully!");
        } else {
            JOptionPane.showMessageDialog(gradeFrame, "No changes made to update.");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(gradeFrame, "Failed to update records.");
    }
}


    
    


private void computeAverage() {
    if (selectedRow != -1) { 
        double comp090 = parseGrade((String) model.getValueAt(selectedRow, 3));
        double comp010 = parseGrade((String) model.getValueAt(selectedRow, 4));
        double comp012 = parseGrade((String) model.getValueAt(selectedRow, 5));
        double comp013 = parseGrade((String) model.getValueAt(selectedRow, 6));
        double comp014 = parseGrade((String) model.getValueAt(selectedRow, 7));
        double elecit_fe2 = parseGrade((String) model.getValueAt(selectedRow, 8));
        double inte202 = parseGrade((String) model.getValueAt(selectedRow, 9));
        double pathfit4 = parseGrade((String) model.getValueAt(selectedRow, 10));

      
        double average = (comp090 + comp010 + comp012 + comp013 + comp014 + elecit_fe2 + inte202 + pathfit4) / 8.0;

      
        String formattedAverage = String.format("%.2f", average);

 
        model.setValueAt(formattedAverage, selectedRow, 11);
    } else {
        JOptionPane.showMessageDialog(gradeFrame, "Please select a row to compute average.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}


private double parseGrade(String grade) {
    try {
        return Double.parseDouble(grade);
    } catch (NumberFormatException e) {
       
        return 0.0;
    }
}
 
}