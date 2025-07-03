package org.example;

public class BankAccount {
    private int accountNumber;
    private String accountHolderName;
    private double availableBalance = 0;

    BankAccount(int accountNumber, String accountHolderName) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
    }

    public int deposit(double amountToDeposit){
        if (amountToDeposit <= 0){
            System.out.println("Enter a valid amount!");
            return 101;
        }
        this.availableBalance += amountToDeposit;
        return 0;
    }
    public int withdraw(double amountToWithdraw){
        if (amountToWithdraw <= 0){
            System.out.println("Enter a valid amount!");
            return 101;
        }
        if (amountToWithdraw > this.availableBalance){
            System.out.println("Insufficient balance!");
            return 102;
        }
        this.availableBalance -= amountToWithdraw;
        return 0;
    }
    public void displayInformation(){
        System.out.println("Account Holder Name: " + this.accountHolderName);
        System.out.println("Account Number: " + this.accountNumber);
        System.out.println("Available Balance: " + this.availableBalance);
    }
    public int getAccountNumber(){
        return this.accountNumber;
    }
    public double getAvailableBalance() {
        return this.availableBalance;
    }
}
