package mylab.bank.entity;

import mylab.bank.exception.InsufficientBalanceException;

public class Account {
    private String accountNumber;
    private String ownerName;
    private double balance;

    public Account(String accountNumber, String ownerName, double balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(amount + "원이 입금되었습니다. 현재 잔액: " + balance + "원");
        } else {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
    }

    // withdraw void throws InsufficientBalanceException
    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > 0) {
            if (balance >= amount) {
                balance -= amount;
                System.out.println(amount + "원이 출금되었습니다. 현재 잔액: " + balance + "원");
            } else {
                throw new InsufficientBalanceException("출금 한도를 초과했습니다. 한도: " + balance + "원");
            }
        } else {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
    }

    // toString method
    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", balance=" + balance +
                '}';
    }   
}
