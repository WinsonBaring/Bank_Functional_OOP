/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bank_Functional_System;

/**
 *
 * @author Winson Baring
 */
public class Verifications {
    private String customerusername;
    private String accountnumber;
    private double amount;
    private int status;
    private String typeoftransaction;

    public Verifications() {
    }

    
    
    public Verifications(String customerusername, String accountnumber, double amount, int status, String typeoftransaction) {
        this.customerusername = customerusername;
        this.accountnumber = accountnumber;
        this.amount = amount;
        this.status = status;
        this.typeoftransaction = typeoftransaction;
    }

    public String getCustomerusername() {
        return customerusername;
    }

    public void setCustomerusername(String customerusername) {
        this.customerusername = customerusername;
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTypeoftransaction() {
        return typeoftransaction;
    }

    public void setTypeoftransaction(String typeoftransaction) {
        this.typeoftransaction = typeoftransaction;
    }
    
    
}
