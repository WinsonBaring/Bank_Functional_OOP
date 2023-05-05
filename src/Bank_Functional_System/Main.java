/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Bank_Functional_System;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;

/**
 *
 * @author L14X09W21
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    Connect conn;
    JMain mainGui;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtUsername;
    
    public static void main(String[] args) {
        // TODO code application logic here
        Login login = new Login();
        login.show(true);
    }

    
    
}
