package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class BankSystem {
    private ArrayList<BankAccount> bankAccounts = new ArrayList<>();
    private BankAccount loggedInAccount;
//    FIND ACCOUNT
    public int findAccount(int accountNumber){
        for (int i = 0; i < this.bankAccounts.size(); i++){
            if (this.bankAccounts.get(i).getAccountNumber() == accountNumber){
                return i;
            }
        }
        System.out.println("Account does not exist!");
        return 404;
    }
//   1) CREATE ACCOUNT
    public int createAccount(int accountNumber, String accountHolderName){
        if (this.findAccount(accountNumber) != -1){
            System.out.println("Account number already exists!");
            return 201;
        }
        bankAccounts.add(new BankAccount(accountNumber, accountHolderName));
        return 0;
    }
//   1) CREATE ACCOUNT
    public int createAccount(int accountNumber, String accountHolderName, double initialDeposit){
        if (this.findAccount(accountNumber) != -1){
            System.out.println("Account number already exists!");
            return 201;
        }
        if (initialDeposit <= 0){
            System.out.println("Enter a valid amount!");
            return 202;
        }
        BankAccount newAccount = new BankAccount(accountNumber, accountHolderName);
        newAccount.deposit(initialDeposit);
        this.loggedInAccount = newAccount;
        bankAccounts.add(newAccount);
        return 0;
    }
//   3) CHECK BALANCE
    public int checkBalance(int accountNumber){
        int index = findAccount(accountNumber);
        if (index == 404){
            return 404;
        }
        System.out.println("Available balance: " + bankAccounts.get(index).getAvailableBalance());
        return 0;
    }
//   2) VIEW ALL ACCOUNTS
    public void viewAllAccounts(){
        for(int i = 0; i < bankAccounts.size(); i++){
            System.out.println("Account " + (i + 1) + ":");
            bankAccounts.get(i).displayInformation();
            System.out.println("");
        }
    }
//  4&5) DEPOSIT AND WITHDRAW
    public int transaction(int accountNumber, String transactionType, double amount){
        int indexOfAccount = findAccount(accountNumber);
        if (indexOfAccount == 404){
            return 404;
        }
        switch (transactionType){
            case "Deposit":{
                return bankAccounts.get(indexOfAccount).deposit(amount);
            }
            case "Withdraw":{
                return bankAccounts.get(indexOfAccount).withdraw(amount);
            }
            default:{
                System.out.println("Invalid transaction type!");
                return 203;
            }
        }
    }
    public void run(){
        Scanner scanner = new Scanner(System.in);
        int option;
        option = scanner.nextInt();
        while (option != 6){
            switch ()
        }
    }
}
