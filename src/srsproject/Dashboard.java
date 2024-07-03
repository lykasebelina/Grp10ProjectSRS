package srsproject;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;



public class Dashboard implements ActionListener {
        JFrame frame;
        JPanel headerPanel;
        JLabel headerLabel;
        JButton button1, button2, button3, button4, logoutButton;
        Image background;
        ImageIcon bgImage;
        ImageIcon bckgrnd;
        JPanel panelBG;
        JLabel imageLabel;
        
        
    public Dashboard () {
        frame = new JFrame();
        frame.setTitle("Dashboard Menu");
        frame.setSize(1500, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

    


        headerPanel = new JPanel();
        headerPanel.setBackground(new Color(128, 0, 0));   
        headerPanel.setBounds(0, 0, 1500, 100);

        
        
        headerLabel = new JLabel("BSIT STUDENT RECORD SYSTEM");
        headerLabel.setPreferredSize(new Dimension(720, 100)); 
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 38));
        headerLabel.setForeground(new Color(245, 245, 220));
        headerPanel.add(headerLabel);

  
        

        button1 = new JButton("Student Information");
        button1.setFont(new Font("Arial Black", Font.BOLD, 28));
        button1.setForeground(new Color(245, 245, 220));
        button1.setBackground(new Color(128, 0, 0));      
        button1.setOpaque(true);
        button1.setBorder(null);
        button1.setBounds(60, 135, 780, 150);
        button1.addActionListener(this);
 
  

        button2 = new JButton("Student Grades");
        button2.setFont(new Font("Arial Black", Font.BOLD, 28));
        button2.setForeground(new Color(245, 245, 220));
        button2.setBackground(new Color(128, 0, 0));
        button2.setOpaque(true);
        button2.setBorder(null);
        button2.setBounds(60, 295, 780, 150); 
        button2.addActionListener(this);

        

        button3 = new JButton("Attendance Record");
        button3.setFont(new Font("Arial Black", Font.BOLD, 28));
        button3.setForeground(new Color(245, 245, 220));
        button3.setBackground(new Color(128, 0, 0));
        button3.setOpaque(true);
        button3.setBorder(null);
        button3.setBounds(60, 455, 780, 150);
        button3.addActionListener(this);

        
        logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial Black", Font.BOLD, 15));
        logoutButton.setForeground(new Color(245, 245, 220));
        logoutButton.setBackground(new Color(128, 0, 0));
        logoutButton.setOpaque(true);
        logoutButton.setBorder(null);
        logoutButton.setBounds(1080, 680, 300, 70);
        logoutButton.addActionListener(this);



        bgImage = new ImageIcon("folderimage/sbBinan.jpg");
        background = bgImage.getImage().getScaledInstance(1500, 1000, Image.SCALE_SMOOTH);
        bckgrnd = new ImageIcon(background);
       
        imageLabel = new JLabel(bckgrnd);
        
        panelBG = new JPanel();
        panelBG.setBounds(0,-10,1500,1000);
        panelBG.add(imageLabel);
        
        

        

        frame.add(headerPanel);
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(logoutButton);
        frame.add(panelBG);
 
        
        //frame.setLocationRelativeTo(null);
        //frame.pack();
        frame.setVisible(true);
    }

    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == logoutButton) {
            frame.dispose(); 
            UserLogin ul = new UserLogin();
            
        } else if (e.getSource() == button1) {
            frame.dispose(); 
            StudentInformation si = new StudentInformation();
            
        } else if (e.getSource() == button2) {
            frame.dispose(); 
            Tablegrade tb = new Tablegrade();
            
        } else if (e.getSource() == button3) {
            frame.dispose(); 
            AttendanceRecord ar = new AttendanceRecord();
            
        }
    }

       
        
        


}

