package srsproject;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MJV Merida
 */

import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class UserLogin implements ActionListener {
    JFrame frame;
    JPanel header;
    JLabel lemail, lpassword, linfo, lheader;
    JTextField email;
    JPasswordField password;
    JButton button1;
    JCheckBox box;

    UserLogin() {
    
        frame = new JFrame();

     
        frame.setSize(1500, 1000);

    
        frame.setTitle("PUPBC Student Record System");
        frame.setLocationRelativeTo(null);

        frame.setLayout(null);

       
        header = new JPanel();
        header.setBounds(0, 0, 1700, 100);

        lheader = new JLabel("BSIT STUDENT RECORD SYSTEM");
        lheader.setPreferredSize(new Dimension(840, 90));
        lheader.setFont(new Font("Bookman Old Style", Font.BOLD, 38));

        button1 = new JButton("Login");
        button1.setFont(new Font("Arial Black", Font.BOLD, 14));
        button1.setBounds(520, 520, 450, 60);
        button1.addActionListener(this);

        lpassword = new JLabel("Password:");
        lpassword.setBounds(550, 280, 170, 80);
        lpassword.setFont(new Font("Arial Black", Font.BOLD, 13));

        linfo = new JLabel("LOGIN");
        linfo.setBounds(650, 120, 390, 90);
        linfo.setFont(new Font("Arial Black", Font.BOLD, 50));

        password = new JPasswordField();
        password.setBounds(650, 300, 300, 40);

        email = new JTextField();
        email.setBounds(650, 240, 300, 40);

        lemail = new JLabel("Email:");
        lemail.setBounds(550, 240, 250, 40);
        lemail.setFont(new Font("Arial Black", Font.BOLD, 13));

        box = new JCheckBox("Show Your Password");
        box.setFont(new Font("Arial Black", Font.BOLD, 12));
        box.setBounds(650, 350, 190, 30);
        box.addActionListener(this);

   
        frame.add(button1);
        frame.add(lemail);
        frame.add(lpassword);
        frame.add(linfo);
        frame.add(password);
        frame.add(email);
        frame.add(box);
        frame.add(header, Integer.valueOf(2));
        header.add(lheader);

    
        frame.getContentPane().setBackground(new Color(255, 239, 213));
        box.setBackground(new Color(255, 239, 213));
        button1.setForeground(new Color(245, 245, 220));
        button1.setBackground(new Color(128, 0, 0));
        header.setBackground(new Color(128, 0, 0));
        lheader.setForeground(new Color(245, 245, 220));
        lheader.setForeground(Color.WHITE);

    
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == box) {
           
            if (box.isSelected()) {
                password.setEchoChar((char) 0);
            } else {
                password.setEchoChar('•');
            }
            
       
        } 
        else if (e.getSource() == button1) {
            String emailtxt = email.getText();
            String passwordtxt = new String(password.getPassword());

            if (emailtxt.isEmpty() || passwordtxt.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter your both Email and Password.", "Login Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String dbUrl = "jdbc:mysql://localhost:3306/user_login";
                String dbUser = "root";
                String dbPassword = "rootmjv_root16";

                try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
                    String query = "SELECT * FROM user_access WHERE user_email = ? AND user_password = ?";
                    PreparedStatement preparedStatement = conn.prepareStatement(query);
                    preparedStatement.setString(1, emailtxt);
                    preparedStatement.setString(2, passwordtxt);
                    
                    ResultSet result = preparedStatement.executeQuery();
                    
                    if(result.next()){  
                        JOptionPane.showMessageDialog(frame, "Login successful!", "Login", JOptionPane.INFORMATION_MESSAGE);
                        Dashboard db = new Dashboard();         
                        frame.dispose();
                    } else{
                        
                        JOptionPane.showMessageDialog(frame, "Invalid Email or Password.", "Login Error", JOptionPane.ERROR_MESSAGE);
                    }


                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Database connection error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                }

            
                
            }
        }
    }

}