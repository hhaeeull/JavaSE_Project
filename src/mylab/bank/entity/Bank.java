package mylab.bank.entity;

import java.util.ArrayList;
import java.util.List;

import mylab.bank.exception.AccountNotFoundException;
import mylab.bank.exception.InsufficientBalanceException;
import mylab.bank.exception.WithdrawalLimitExceededException;

public class Bank {
    private List<Account> accounts;
    private int nextAccountNumber;

    public Bank() {
        this.accounts = new ArrayList<>();
        this.nextAccountNumber = 1000;
    }

    // createSavingsAccount
    public String createSavingsAccount(String ownerName, double initialBalance, double interestRate) {
        String accountNumber = generateAccountNumber();
        SavingsAccount account = new SavingsAccount(accountNumber, ownerName, initialBalance, interestRate);
        accounts.add(account);
        return accountNumber;
    }

    private String generateAccountNumber() {
        return String.format("AC%04d", nextAccountNumber++);
    }

    // createCheckingAccount
    public String createCheckingAccount(String ownerName, double initialBalance, double withdrawalLimit) {
        String accountNumber = generateAccountNumber();
        CheckingAccount account = new CheckingAccount(accountNumber, ownerName, initialBalance, withdrawalLimit);
        accounts.add(account);
        return accountNumber;
    }

    // findAccount : Account throws AccountNotFoundException
    public Account findAccount(String accountNumber) throws AccountNotFoundException {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        throw new AccountNotFoundException("계좌번호 " + accountNumber + "에 해당하는 계좌를 찾을 수 없습니다.");
    }

    // deposit : void throws AccountNotFoundException
    public void deposit(String accountNumber, double amount) throws AccountNotFoundException {
        Account account = findAccount(accountNumber);
        account.deposit(amount);
    }

    // withdraw : void throws account and withdrawal exceptions
    public void withdraw(String accountNumber, double amount) throws AccountNotFoundException, InsufficientBalanceException, WithdrawalLimitExceededException {
        Account account = findAccount(accountNumber);
        if (account instanceof CheckingAccount checkingAccount) {
            if (amount > account.getBalance()) {
                throw new InsufficientBalanceException("출금 한도를 초과했습니다. 한도: " + checkingAccount.getWithdrawalLimit() + "원");
            }
            if (amount > checkingAccount.getWithdrawalLimit()) {
                throw new WithdrawalLimitExceededException("출금 한도를 초과했습니다. 한도: " + checkingAccount.getWithdrawalLimit() + "원");
            }
        }
        account.withdraw(amount);
    }

    // transfer : void throws account and withdrawal exceptions
    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) throws AccountNotFoundException, InsufficientBalanceException, WithdrawalLimitExceededException {
        Account toAccount = findAccount(toAccountNumber);
        withdraw(fromAccountNumber, amount);
        toAccount.deposit(amount);
        System.out.println(amount + "원이 " + fromAccountNumber + "에서 "
                + toAccountNumber + "로 송금되었습니다.");
    }

    // printAllAccounts : void
    public void printAllAccounts() {
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

}
