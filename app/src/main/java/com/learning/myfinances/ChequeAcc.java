package com.learning.myfinances;

//helper class to store edit text values
public class ChequeAcc {

    int accNumber;
    double initBalance, currentBalance,  interestRate, paymentAmt;

    public ChequeAcc(String accNum, String initBalance, String currentBalance, String interestRate, String paymentAmt){
        this.accNumber = Integer.parseInt(accNum);
        this.currentBalance = Double.parseDouble(currentBalance);
        this.initBalance = Double.parseDouble(initBalance);
        this.interestRate = Double.parseDouble(interestRate);
        this.paymentAmt = Double.parseDouble(paymentAmt);
    }
}
