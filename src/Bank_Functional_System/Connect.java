/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bank_Functional_System;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 * 
 *
 * @author L14X09W21
 */
public class Connect {
    Connection conn=null;
    public Connect(){
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","");
            //JOptionPane.showMessageDialog(null, "Connected");
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean registerUser(User user){
        Statement stmt;
        String sql=null;
        ResultSet rs=null;
        try {
            stmt = conn.createStatement();
            sql="select * from user where username='"+user.getUsername()+"'";
            rs =stmt.executeQuery(sql);
            if(rs.next()==false){
                sql="insert into user values('"+user.getUsername()+"','"+user.getPassword()+"','"+user.getFirstname()+"','"+user.getLastname()+"','"+user.getUserType()+"')";
                stmt.executeUpdate(sql);
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    public int login(String username, String password){
        Statement stmt;
        String sql;
        ResultSet rs;
        try {
            stmt=conn.createStatement();
            sql ="select * from user where username='"+username+"' and password='"+password+"'";
            rs = stmt.executeQuery(sql);
            if (rs.next()==true){
                if(rs.getInt(5) == 1){
                    return 2;
                }else{
                    return 1;
                }
            }
            else
                return 0;
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public ArrayList<Account> displayAccount(String username){
        ArrayList<Account> acc = new ArrayList<Account>();
        String sql ="select * from account where username='"+username+"'";
        Statement stmt;
        ResultSet rs;
        
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
              Account a = new Account(rs.getString(1),rs.getDouble(2)) ;
              acc.add(a);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return acc;
    }
    
    public boolean updateBalance(Account account){
        Statement stmt ;
        ResultSet rs;
        String sql = "select * from user";
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next() == true){
                JOptionPane.showMessageDialog(null, "sadf");
            }
        }catch(SQLException ex){
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }
    
    public void deleteAccount(Account account){
    }
    
}

