/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package srsproject;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class Addgrade implements ActionListener {
    JTextField txtname, txtcomp090, txtcomp010, txtcomp012, txtcomp013, txtcomp014, txtelec, txtinte, txtpathfit;
    JButton btnSave, btnReturn;
    JPanel headerPanel;

    private static final String dbURL = "jdbc:mysql://localhost:3306/user_login";
    private static final String dbUser = "root";
    private static final String dbPass = "rootmjv_root16";
    
    Addgrade(){

        
        JFrame grades = new JFrame();
        grades.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        grades.setSize(1500,1000);
        grades.setTitle("GRADES RECORD");
        grades.setLocationRelativeTo(null);
        grades.setLayout(null);
        
        headerPanel = new JPanel();
        headerPanel.setBackground(new Color(128, 0, 0));   
        headerPanel.setBounds(0, 0, 1500, 100);
        
        
        JLabel lblHeader = new JLabel("STUDENT MANAGEMENT SYSTEM");
        lblHeader.setBounds(400, 30, 700, 50);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 38));
        lblHeader.setForeground(Color.WHITE);
        
        JLabel lblHeader2 = new JLabel("EDIT GRADES");
        lblHeader2.setBounds(625, 130, 500, 50);
        lblHeader2.setFont(new Font("Arial Black", Font.BOLD, 33));
        lblHeader2.setForeground(Color.BLACK);
        JLabel lblName = new JLabel("Student Name: ");
        lblName.setBounds(300, 260, 200, 50);
        lblName.setFont(new Font("serif", Font.BOLD, 20));
        lblName.setForeground(Color.BLACK);
        txtname = new JTextField();
        txtname.setBounds(450, 270, 400, 35);
        
        
        JLabel lblSub1 = new JLabel("COMP 090: ");
        lblSub1.setBounds(300, 310, 200, 50);
        lblSub1.setFont(new Font("serif", Font.BOLD, 20));
        lblSub1.setForeground(Color.BLACK);
        txtcomp090 = new JTextField();
        txtcomp090.setBounds(450, 320, 400, 35);
        JLabel lblSub2 = new JLabel("COMP 010: ");
        lblSub2.setBounds(300, 360, 200, 50);
        lblSub2.setFont(new Font("serif", Font.BOLD, 20));
        lblSub2.setForeground(Color.BLACK);
        txtcomp010 = new JTextField();
        txtcomp010.setBounds(450, 370, 400, 35);
        JLabel lblSub3 = new JLabel("COMP 012: ");
        lblSub3.setBounds(300, 410, 200, 50);
        lblSub3.setFont(new Font("serif", Font.BOLD, 20));
        lblSub3.setForeground(Color.BLACK);
        txtcomp012 = new JTextField();
        txtcomp012.setBounds(450, 420, 400, 35);
        JLabel lblSub4 = new JLabel("COMP 013: ");
        lblSub4.setBounds(300, 460, 200, 50);
        lblSub4.setFont(new Font("serif", Font.BOLD, 20));
        lblSub4.setForeground(Color.BLACK);
        txtcomp013 = new JTextField();
        txtcomp013.setBounds(450, 470, 400, 35);
        JLabel lblSub5 = new JLabel("COMP 014: ");
        lblSub5.setBounds(300, 510, 200, 50);
        lblSub5.setFont(new Font("serif", Font.BOLD, 20));
        lblSub5.setForeground(Color.BLACK);
        txtcomp014 = new JTextField();
        txtcomp014.setBounds(450, 520, 400, 35);
        JLabel lblSub6 = new JLabel("ELEC IT-FE2: ");
        lblSub6.setBounds(300, 560, 200, 50);
        lblSub6.setFont(new Font("serif", Font.BOLD, 20));
        lblSub6.setForeground(Color.BLACK);
        txtelec = new JTextField();
        txtelec.setBounds(450, 570, 400, 35);
        JLabel lblSub7 = new JLabel("INTE 202: ");
        lblSub7.setBounds(300, 610, 200, 50);
        lblSub7.setFont(new Font("serif", Font.BOLD, 20));
        lblSub7.setForeground(Color.BLACK);
        txtinte = new JTextField();
        txtinte.setBounds(450, 620, 400, 35);
        JLabel lblSub8 = new JLabel("PATHFIT 4: ");
        lblSub8.setBounds(300, 660, 200, 50);
        lblSub8.setFont(new Font("serif", Font.BOLD, 20));
        lblSub8.setForeground(Color.BLACK);
        txtpathfit = new JTextField();
        txtpathfit.setBounds(450, 670, 400, 35);
        
        
        btnSave = new JButton("SAVE");
        btnSave.setBounds(1000, 300, 300, 50);
        btnSave.setFont(new Font("Arial Black", Font.BOLD, 13));
        btnSave.setBackground(new Color(245, 245, 220));
        btnSave.setForeground(new Color(128, 0, 0));
        btnSave.addActionListener(this);
        
        btnReturn = new JButton("BACK");
        btnReturn.setBounds(1000, 600, 300, 50);
        btnReturn.setFont(new Font("Arial Black", Font.BOLD, 13));
        btnReturn.setBackground(new Color(245, 245, 220));
        btnReturn.setForeground(new Color(128, 0, 0));
        btnReturn.addActionListener(this);
        
        
        grades.add(lblHeader);
        grades.add(lblHeader2);
        grades.add(lblName);
        grades.add(txtname);
        grades.add(lblSub1);
        grades.add(txtcomp090);
        grades.add(lblSub2);
        grades.add(txtcomp010);
        grades.add(lblSub3);
        grades.add(txtcomp012);
        grades.add(lblSub4);
        grades.add(txtcomp013);
        grades.add(lblSub5);
        grades.add(txtcomp014);
        grades.add(lblSub6);
        grades.add(txtelec);
        grades.add(lblSub7);
        grades.add(txtinte);
        grades.add(lblSub8);
        grades.add(txtpathfit);
        grades.add(btnSave);
        grades.add(btnReturn);
        grades.add(headerPanel);

        grades.setLayout(null);
        grades.setVisible(true);
        
        JLabel background = new JLabel();
        ImageIcon bckgrndPic = new ImageIcon("folderimage/sbBinan.jpg");
        background.setIcon(bckgrndPic);
        background.setSize(1500, 1000);
        grades.add(background);
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == btnReturn){
            Tablegrade tbl = new Tablegrade();
        }
        
        else if(e.getSource() == btnSave){
            String Studname, comp090, comp010, comp012, comp013, comp014, elec, inte, pathfit, gwa;
    
            Studname = txtname.getText();
            comp090 = txtcomp090.getText();
            comp010 = txtcomp010.getText();
            comp012 = txtcomp012.getText();
            comp013 = txtcomp013.getText();
            comp014 = txtcomp014.getText();
            elec = txtelec.getText();
            inte = txtinte.getText();
            pathfit = txtpathfit.getText();
    
            double dcomp090 = Double.parseDouble(comp090);
            double dcomp010 = Double.parseDouble(comp010);
            double dcomp012 = Double.parseDouble(comp012);
            double dcomp013 = Double.parseDouble(comp013);
            double dcomp014 = Double.parseDouble(comp014);
            double delec = Double.parseDouble(elec);
            double dinte = Double.parseDouble(inte);
            double dpathfit = Double.parseDouble(pathfit);
        
            double ave = (dcomp090 + dcomp010 + dcomp012 + dcomp013 + dcomp014 + delec + dinte + dpathfit) / 8;
        
            gwa = Double.toString(ave);
        
            try(Connection connection = DriverManager.getConnection(dbURL, dbUser, dbPass)){
                String sql = "INSERT INTO gradesystem (Student_Name, COMP090, COMP010, COMP012, COMP013, COMP014, ELECIT_FE2, INTE202, PATHFIT4, GWA) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
                PreparedStatement ps = connection.prepareStatement(sql);
                    ps.setString(1, Studname);
                    ps.setString(2, comp090);
                    ps.setString(3, comp010);
                    ps.setString(4, comp012);
                    ps.setString(5, comp013);
                    ps.setString(6, comp014);
                    ps.setString(7, elec);
                    ps.setString(8, inte);
                    ps.setString(9, pathfit);
                    ps.setString(10, gwa);
                
                    int x = ps.executeUpdate();
                    if (x > 0) {
                        JOptionPane.showMessageDialog(null, x + " Rows affected");
                    } else {
                        JOptionPane.showMessageDialog(null, "Insert Failed");
                    }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database error!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } 
}