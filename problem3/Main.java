package problem3;

import problem3.accounts.Account;
import problem3.accounts.CheckingAccount;
import problem3.accounts.OverdraftAccount;
import problem3.accounts.TimeDepositAccount;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("#,###");

        // 길동이의 계좌
        Map<Integer, Account> accounts = new HashMap<Integer, Account>();
        accounts.put(1, new CheckingAccount(1, "홍길동", 0, 0));
        accounts.put(2, new TimeDepositAccount(2, "홍길동", 50_000_000, 0, 48));
        accounts.put(3, new OverdraftAccount(3, "홍길동", 0));

        while (true) {
            System.out.print(">> 통장을 선택하세요(1: 자유입출금, 2.정기예금, 3:마이너스) ");
            String input = sc.nextLine();
            // 뒤로가기
            if (input.isBlank() || input.equals("0")) {
                System.out.println("금일 OneHanaBank는 업무를 종료합니다. 감사합니다.");
                break;
            // 통장 선택
            } else if (input.equals("1") || input.equals("2") || input.equals("3")) {
                Account account = accounts.get(Integer.parseInt(input));
                System.out.println(account.toString());
                while (true) {
                    System.out.print("> 원하시는 업무는? (+: 입금, -: 출금, T: 이체, I: 정보) ");
                    input = sc.nextLine();
                    // 뒤로가기
                    if (input.isBlank() || input.equals("0")) {
                        break;
                    // 입금
                    } else if (input.equals("+")) {
                        while(true) {
                            System.out.print("입금하실 금액은? ");
                            input = sc.nextLine();
                            if (input.isBlank() || input.equals("0")) {
                                break;
                            } else {
                                if (input.matches("\\d+")) {
                                    int amount = Integer.parseInt(input);
                                    account.deposit(amount);
                                    System.out.printf("%s에 %s원이 입금되었습니다!", account.getAccountName(), df.format(amount));
                                }
                            }
                        }
                    // 출금
                    } else if (input.equals("-")) {
                        while (true) {
                            System.out.print("출금하실 금액은? ");
                            input = sc.nextLine();
                            if (input.isBlank() || input.equals("0")) {
                                break;
                            } else {
                                if (input.matches("\\d+")) {
                                    int amount = Integer.parseInt(input);
                                    account.withdraw(amount);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
