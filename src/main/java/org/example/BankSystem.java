package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class BankSystem {
    private ArrayList<BankAccount> bankAccounts = new ArrayList<>();
//    FIND ACCOUNT
    public int findAccount(int accountNumber){
        for (int i = 0; i < this.bankAccounts.size(); i++){
            if (this.bankAccounts.get(i).getAccountNumber() == accountNumber){
                return i;
            }
        }
//
        return 404;
    }
//   1) CREATE ACCOUNT
    public int createAccount(int accountNumber, String accountHolderName){
        if (this.findAccount(accountNumber) == -1 && this.bankAccounts.size() !=0){
            System.out.println("Account number already exists!");
            return 201;
        } else {
            bankAccounts.add(new BankAccount(accountNumber, accountHolderName));
            System.out.println("Account created!");
            return 0;
        }

    }
//   1) CREATE ACCOUNT
    public int createAccount(int accountNumber, String accountHolderName, double initialDeposit){
//        this.createAccount(accountNumber, accountHolderName);
//        if (this.findAccount(accountNumber) != -1){
//            System.out.println("Account number already exists!");
//            return 201;
//        }
//        if (initialDeposit <= 0){
//            System.out.println("Enter a valid amount!");
//            return 202;
//        }
        BankAccount newAccount = new BankAccount(accountNumber, accountHolderName);
        newAccount.deposit(initialDeposit);
        bankAccounts.add(newAccount);
        System.out.println("Account created!");
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
        System.out.println("=== Bank Menu ===");
        System.out.println("1. Create Account");
        System.out.println("2. View All Accounts");
        System.out.println("3. Check Balance");
        System.out.println("4. Deposit");
        System.out.println("5. Withdraw");
        System.out.println("6. Exit");
        System.out.print("Enter Option: ");
        int option = scanner.nextInt();
        while (option != 6){
            switch (option){
                case 1:{
                    scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String accountHolderName = scanner.nextLine();
                    System.out.print("Enter Account Number: ");
                    int accountNumber = scanner.nextInt();
                    System.out.print("Will Make An Initial Deposit? (Y/N): ");
                    String makeInitialDeposit = scanner.next();
                    boolean willMakeInitialDeposit;
                    if (makeInitialDeposit.equals("Y")){
                        willMakeInitialDeposit = true;

                    } else if (makeInitialDeposit.equals("N")) {
                        willMakeInitialDeposit = false;
                    } else {
                        willMakeInitialDeposit = false;
                    }
                    if (willMakeInitialDeposit){
                        System.out.print("Initial Deposit: ");
                        double initialDeposit = scanner.nextInt();
                        while (initialDeposit <= 0){
                            System.out.println("Enter valid amount!");
                            System.out.print("Initial Deposit: ");
                            initialDeposit = scanner.nextDouble();
                        }
                        while (this.createAccount(accountNumber, accountHolderName, initialDeposit) == 201) {
                            System.out.println("Invalid Account Number!");
                            System.out.print("Enter Account Number: ");
                            accountNumber = scanner.nextInt();
                        }
                    } else {
                        while (this.createAccount(accountNumber, accountHolderName) == 201){
                            System.out.println("Invalid Account Number!");
                            System.out.print("Enter Account Number: ");
                            accountNumber = scanner.nextInt();
                        }
                    }
                    break;
                }
                case 2:{
                    this.viewAllAccounts();
                    break;
                }
                case 3:{
                    System.out.print("Enter Account Number: ");
                    int accountNumber = scanner.nextInt();
                    while (checkBalance(accountNumber) == 404){
                        System.out.println("Invalid Account Number!");
                        System.out.print("Enter Account Number: ");
                        accountNumber = scanner.nextInt();
                    }
                    break;
                }
                case 4:{
                    System.out.print("Enter Account Number: ");
                    int accountNumber = scanner.nextInt();
                    while (this.findAccount(accountNumber) == 404){
                        System.out.println("Invalid Account Number!");
                        System.out.print("Enter Account Number: ");
                        accountNumber = scanner.nextInt();
                    }
                    System.out.print("Enter Amount to Deposit: ");
                    double amount = scanner.nextDouble();
                    while (transaction(accountNumber, "Deposit", amount) != 0){
                        System.out.print("Enter Amount to Deposit: ");
                        amount = scanner.nextDouble();
                    }
                    break;
                }
                case 5:{
                    System.out.print("Enter Account Number: ");
                    int accountNumber = scanner.nextInt();
                    while (this.findAccount(accountNumber) == 404){
                        System.out.println("Invalid Account Number!");
                        System.out.print("Enter Account Number: ");
                        accountNumber = scanner.nextInt();
                    }
                    System.out.print("Enter Amount to Withdraw: ");
                    double amount = scanner.nextDouble();
                    while (transaction(accountNumber, "Withdraw", amount) != 0){
                        System.out.print("Enter Amount to Withdraw: ");
                        amount = scanner.nextDouble();
                    }
                    break;
                }
                default:{
                    System.out.println("Choose a valid option!");
                }

            }
            System.out.print("Would you like to return to the menu? (yes/no): ");
            String response = scanner.next();
            if (response.equals("no")){
                break;
            } else if (response.equals("yes")){
                System.out.println("=== Bank Menu ===");
                System.out.println("1. Create Account");
                System.out.println("2. View All Accounts");
                System.out.println("3. Check Balance");
                System.out.println("4. Deposit");
                System.out.println("5. Withdraw");
                System.out.println("6. Exit");
                System.out.print("Enter Option: ");
                option = scanner.nextInt();
            }

        }
        System.out.println("Thank you for using RCASH!");
    }
}
