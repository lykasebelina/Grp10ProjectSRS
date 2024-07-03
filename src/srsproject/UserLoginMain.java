package srsproject;

import java.awt.EventQueue;


public class UserLoginMain {
   
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            
            public void run() {
           UserLogin ul = new UserLogin();
           ul.setVisible(true);
    }
        });
        
    }
}

                
