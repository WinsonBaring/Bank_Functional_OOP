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
    public ArrayList<Verifications> displayverify(){
        Statement stmt= null;
        ResultSet rs = null;
        String sql = null;
        ArrayList<Verifications> verify = new ArrayList<Verifications>();
        try{
            sql = "SELECT * from verification";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                Verifications ver = new Verifications(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getInt(5),rs.getString(4));
                verify.add(ver);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Verificaiton Error");
        }
        return verify;
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
    public boolean verify(Account account){
        Statement stmt;
        ResultSet rs;
        String sql;
        try{
            sql = "Update account set balance = "+account.getBalance()+" WHERE accountNumber = '"+account.getAccountNumber()+"' and username = '"+account.getUsername()+"' ";
            stmt = conn.createStatement();
            int accountupdate = stmt.executeUpdate(sql);
            if( accountupdate == 1){
            }
            sql = "delete from verification where customerusername = '"+account.getUsername()+"' && accountnumber = '"+account.getAccountNumber()+"'  ";
            stmt = conn.createStatement();
            int verifyupdate = stmt.executeUpdate(sql);
            if(accountupdate == 1 && verifyupdate == 1){
                JOptionPane.showMessageDialog(null, "Succesfully Processed" );
                return true;
            }
            JOptionPane.showMessageDialog(null, "Fail to process" );
            return false;
        }catch(SQLException ex){
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean updateBalance(Account account,String username){
        Statement stmt ;
        ResultSet rs;
        String sql ;
        try{
            sql = "select * from verification where accountnumber = '"+account.getAccountNumber()+"' && customerusername = '"+username+"' ";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if(rs.next()) {
                JOptionPane.showMessageDialog(null, "Pending " +rs.getString("transactiontype") + " transaction is being process.");
                return false;
            }
            sql = "insert into verification (`customerusername`, `accountnumber`, `amount`, `transactiontype`, `status`) VALUES ('"+username+"','"+account.getAccountNumber()+"',"+account.getBalance()+",'UPDATE',0)";
            stmt = conn.createStatement();
            int up = stmt.executeUpdate(sql);
            System.out.println(up);
            if(up == 1){
                JOptionPane.showMessageDialog(null, "Transaction Succesfully Proccesed");  
                return true;
            }
        }catch(SQLException ex){
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }
    
    public boolean deleteAccount(Account account,String username){
        Statement stmt ;
        ResultSet rs;
        String sql ;
        try{
            sql = "select * from verification where accountnumber = '"+account.getAccountNumber()+"' or customerusername = '"+username+"' ";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if(rs.next()) {
                JOptionPane.showMessageDialog(null, "Pending " +rs.getString("transactiontype") + " transaction is being process.");
                return false;
            }
            sql = "insert into verification (`customerusername`, `accountnumber`, `amount`, `transactiontype`, `status`) VALUES ('"+username+"','"+account.getAccountNumber()+"',"+account.getBalance()+",' DELETE ',0)";
            stmt = conn.createStatement();
            int up = stmt.executeUpdate(sql);
            System.out.println(up);
            if(up == 1){
                JOptionPane.showMessageDialog(null, "Transaction Succesfully Proccesed");             
                return true;
            }
        }catch(SQLException ex){
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }
    
}

