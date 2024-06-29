
package srsproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author acer
 */
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
     JTextField searchBar;
     ImageIcon bg;
     Image imageSize;
     ImageIcon backgroundImage;
     JPanel panelImage;
     JLabel imageLabel;
     
    public StudentInformation() {
    StudentInformationFrame = new JFrame();
    StudentInformationFrame.setTitle("Student Information");
    StudentInformationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    StudentInformationFrame.setPreferredSize(new Dimension(1500, 1000));
    StudentInformationFrame.setLayout(null);
    StudentInformationFrame.setLocationRelativeTo(null);
  
        
        
        headerPanel = new JPanel();
        headerPanel.setBackground(new Color(128, 0, 0));   
        headerPanel.setBounds(0, 0, 1500, 100);
        
        headerLabel = new JLabel("BSIT STUDENT RECORD SYSTEM");
        headerLabel.setPreferredSize(new Dimension(720, 100)); 
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 38));
        headerLabel.setForeground(new Color(245, 245, 220));
        headerPanel.add(headerLabel);
        
        model = new DefaultTableModel(new Object[]{"Name", "Course", "Student Id", "Age", "Gender", "Contact Number ", "Email"}, 0);
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

       String[] choices = { "Male", "Female"};
       JComboBox<String> Gender = new JComboBox<String>(choices);
       table.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(Gender));
    
    
 
       
         searchBar = new JTextField(20);
         
         
         JPanel panelText = new JPanel(new BorderLayout());
         panelText.add(searchBar);
         panelText.setBounds(50, 120, 300, 30);
       
              StudentInformationFrame.add(panelText);
    
        addButton = new JButton("Add New Record");
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
        
        
        
        bg = new ImageIcon("folderimage/sbBinan.jpg");
        imageSize = bg.getImage().getScaledInstance(1500, 1000, Image.SCALE_SMOOTH);
        backgroundImage = new ImageIcon(imageSize);
       
        imageLabel = new JLabel(backgroundImage);
        
        JPanel panelImage = new JPanel();
        panelImage.setBounds(0,-10,1500,1000);
        panelImage.add(imageLabel);
        
        
        StudentInformationFrame.add(scrollPane);
        StudentInformationFrame.add(headerPanel);
        StudentInformationFrame.add(addButton);
        StudentInformationFrame.add(deleteButton);
        StudentInformationFrame.add(backButton);
        StudentInformationFrame.add(panelImage);
        

        StudentInformationFrame.pack();
        StudentInformationFrame.setVisible(true);  
        
    }
  
    private void addRow() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(new Date());

        model.addRow(new Object[]{"","BSIT 2-1","", "", "Set Gender", "",""});
        
     

    }
        @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()== addButton){
                addRow();
            }
        
   
                if(e.getSource()== deleteButton){
               JOptionPane.showMessageDialog(this, "Do you want to delete this record?");
            }
                
                if(e.getSource()== backButton){
               JOptionPane.showMessageDialog(this, backButton.getText() + " clicked");
            }
        }
}


    

