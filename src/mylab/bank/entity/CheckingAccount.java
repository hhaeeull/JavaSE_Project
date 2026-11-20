package mylab.bank.entity;

import mylab.bank.exception.InsufficientBalanceException;

public class CheckingAccount extends Account {
    private double withdrawalLimit;

    public CheckingAccount(String accountNumber, String ownerName, double initialBalance, double withdrawalLimit) {
        super(accountNumber, ownerName, initialBalance);
        this.withdrawalLimit = withdrawalLimit;
    }

    public double getWithdrawalLimit() {
        return withdrawalLimit;
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > 0) {
            if (getBalance() + withdrawalLimit >= amount) {
                super.withdraw(amount);
            } else {
                throw new InsufficientBalanceException("출금 한도를 초과했습니다. 한도: " + withdrawalLimit + "원");
            }
        } else {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
    }

    @Override
    public String toString() {
        return "계좌번호: " + getAccountNumber() + ", 소유자: " + getOwnerName() + ", 잔액: " + getBalance() + ", 출금 한도: " + withdrawalLimit + "원";
    }
}
