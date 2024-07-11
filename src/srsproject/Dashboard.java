package srsproject;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;



public class Dashboard implements ActionListener {
        JFrame frame;
        JPanel headerPanel, panelBG, menuPanel;
        JLabel headerLabel, imageLabel, menuLbl, profileIconLabel, helloLbl, wlcmLbl;
        JButton studinfoBTN, gradesBTN, attendanceBTN,  logoutButton;
        Image background, Image;
        ImageIcon bgImage, bckgrnd, profileIcon, newProfileIcon;

        
    public Dashboard () {
        frame = new JFrame();
        frame.setTitle("Dashboard Menu");
        frame.setSize(1500, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        

        headerPanel = new JPanel();
        headerPanel.setBackground(new Color(128, 0, 0));   
        headerPanel.setBounds(0, 0, 1500, 100);

        
        
        headerLabel = new JLabel("BSIT STUDENT RECORD SYSTEM");
        headerLabel.setPreferredSize(new Dimension(720, 100)); 
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 38));
        headerPanel.add(headerLabel);
        
        
    menuPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Dimension arcs = new Dimension(70, 70);
                int width = getWidth();
                int height = getHeight();
                Graphics2D graphics = (Graphics2D) g;
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                
                graphics.setColor(new Color(128, 0, 0, 150));
                graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); 
                graphics.setColor(new Color(128, 0, 0));
                graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); 
            }
        };
        menuPanel.setBounds(50, 145, 600, 600);
        menuPanel.setOpaque(false);
        menuPanel.setLayout(null);


        profileIcon = new ImageIcon("folderimage/ProfileIcon.png");
        Image = profileIcon.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH); 
        newProfileIcon = new ImageIcon(Image);

        
        profileIconLabel = new JLabel(newProfileIcon);
        profileIconLabel.setBounds(220, 110, 160, 160);
        menuPanel.add(profileIconLabel);
        
        
        
        
       helloLbl = new JLabel("HELLO ADMIN!");
       helloLbl.setBounds(115, 240, 400, 160);
       helloLbl.setForeground(Color.WHITE);
       helloLbl.setFont(new Font("Arial", Font.BOLD, 50));
       menuPanel.add(helloLbl);
       
       wlcmLbl = new JLabel("WELCOME TO BSIT STUDENT RECORD SYSTEM");
       wlcmLbl.setBounds(65, 290, 600, 160);
       wlcmLbl.setForeground(Color.WHITE);
       wlcmLbl.setFont(new Font("Arial", Font.BOLD, 20));
       menuPanel.add(wlcmLbl);
       
       
        

       
        

        studinfoBTN = new JButton("Student Information");
        studinfoBTN.setFont(new Font("Arial Black", Font.BOLD, 28));
        studinfoBTN.setForeground(new Color(245, 245, 220));
        studinfoBTN.setBackground(new Color(128, 0, 0));      
        studinfoBTN.setOpaque(true);
        studinfoBTN.setBorder(null);
        studinfoBTN.setBounds(690, 230, 700, 130);
        studinfoBTN.addActionListener(this);
 
  

        gradesBTN = new JButton("Student Grades");
        gradesBTN.setFont(new Font("Arial Black", Font.BOLD, 28));
        gradesBTN.setForeground(new Color(245, 245, 220));
        gradesBTN.setBackground(new Color(128, 0, 0));
        gradesBTN.setOpaque(true);
        gradesBTN.setBorder(null);
        gradesBTN.setBounds(690, 380, 700, 130); 
        gradesBTN.addActionListener(this);

        

        attendanceBTN = new JButton("Attendance Record");
        attendanceBTN.setFont(new Font("Arial Black", Font.BOLD, 28));
        attendanceBTN.setForeground(new Color(245, 245, 220));
        attendanceBTN.setBackground(new Color(128, 0, 0));
        attendanceBTN.setOpaque(true);
        attendanceBTN.setBorder(null);
        attendanceBTN.setBounds(690, 530, 700, 130);
        attendanceBTN.addActionListener(this);

        
        logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial Black", Font.BOLD, 15));
        logoutButton.setForeground(new Color(128, 0, 0));
        logoutButton.setBackground(Color.WHITE);
        logoutButton.setBounds(240, 580, 220, 50);
        logoutButton.addActionListener(this);


        
     
        
        
        
        

        bgImage = new ImageIcon("folderimage/sbBinan.jpg");
        background = bgImage.getImage().getScaledInstance(1500, 1000, Image.SCALE_SMOOTH);
        bckgrnd = new ImageIcon(background);
       
        imageLabel = new JLabel(bckgrnd);
        
        panelBG = new JPanel();
        panelBG.setBounds(0,-10,1500,1000);
        panelBG.add(imageLabel);
        
        

        

        frame.add(headerPanel);
        frame.add(studinfoBTN);
        frame.add(gradesBTN);
        frame.add(attendanceBTN);
        frame.add(logoutButton);
        frame.add(menuPanel);
        
        frame.add(panelBG);
 
        
        
        frame.setVisible(true);
    }

    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == logoutButton) {
            frame.dispose(); 
            UserLogin ul = new UserLogin();
            
        } else if (e.getSource() == studinfoBTN) {
            frame.dispose(); 
            StudentInformation si = new StudentInformation();
            
        } else if (e.getSource() == gradesBTN) {
            frame.dispose(); 
            Tablegrade tb = new Tablegrade();
            
        } else if (e.getSource() == attendanceBTN) {
            frame.dispose(); 
            AttendanceRecord ar = new AttendanceRecord();
            
        }
    }



}

