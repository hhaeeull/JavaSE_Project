package mylab.bank.control;

import mylab.bank.entity.Bank;
import mylab.bank.entity.CheckingAccount;
import mylab.bank.entity.SavingsAccount;
import mylab.bank.exception.AccountNotFoundException;
import mylab.bank.exception.InsufficientBalanceException;
import mylab.bank.exception.WithdrawalLimitExceededException;

// BankDemo.java - 테스트 클래스
public class BankDemo {
    public static void main(String[] args) {
        Bank bank = new Bank();
        
        try {
            // 계좌 생성
        	System.out.println("=== 계좌 생성 ===");
            
            SavingsAccount savingsAccount1 = (SavingsAccount) bank.findAccount(
                    bank.createSavingsAccount("홍길동", 10000, 0.03));
            System.out.println("Saving(저축) 계좌가 생성되었습니다: " + savingsAccount1);

            CheckingAccount checkingAccount1 = (CheckingAccount) bank.findAccount(
                    bank.createCheckingAccount("김철수", 20000, 5000));
            System.out.println("체킹 계좌가 생성되었습니다: " + checkingAccount1);

            SavingsAccount savingsAccount2 = (SavingsAccount) bank.findAccount(
                    bank.createSavingsAccount("이영희", 30000, 0.02));
            System.out.println("저축 계좌가 생성되었습니다: " + savingsAccount2);
            System.out.println();

            System.out.println("=== 모든 계좌 목록 ===");
            bank.printAllAccounts();
            System.out.println();
            
            // 입금/출금 테스트
            System.out.println("=== 입금/출금 테스트 ===");
            double depositAmount = 5000.0;
            savingsAccount1.deposit(depositAmount);

            double withdrawalAmount = 3000.0;
            checkingAccount1.withdraw(withdrawalAmount);
            System.out.println();
            
            // 이자 적용 테스트
            System.out.println("=== 이자 적용 테스트 ===");
            savingsAccount1.applyInterest();
            System.out.println();
            
            
            // 계좌 이체 테스트
            System.out.println("=== 계좌 이체 테스트 ===");
            bank.transfer(savingsAccount2.getAccountNumber(), checkingAccount1.getAccountNumber(), 5000);
            System.out.println();
            
            // 계좌 이체 이후
            System.out.println("=== 모든 계좌 목록 ===");
            bank.printAllAccounts();
            System.out.println();

            // 예외 테스트
            System.out.println("===================");
            try {
                bank.withdraw(checkingAccount1.getAccountNumber(), 50000); // 잔액 부족
            } catch (InsufficientBalanceException e) {
                System.out.println("예외 발생: " + e.getMessage());
            }
            
            try {
                bank.withdraw(checkingAccount1.getAccountNumber(), 10000); // 출금 한도 초과
            } catch (WithdrawalLimitExceededException e) {
                System.out.println("예외 발생: " + e.getMessage());
            }
            
            try {
                bank.findAccount("AC9999"); // 존재하지 않는 계좌
            } catch (AccountNotFoundException e) {
                System.out.println("예외 발생: " + e.getMessage());
            }
            
        } catch (Exception e) {
            System.out.println("오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
}