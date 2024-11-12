package problem3;

import problem3.accounts.Account;
import problem3.accounts.CheckingAccount;
import problem3.accounts.OverdraftAccount;
import problem3.accounts.TimeDepositAccount;

import java.text.DecimalFormat;
import java.util.*;


public class Main {
    static DecimalFormat df = new DecimalFormat("#,###");
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // 길동이의 계좌
        Map<Integer, Account> accounts = new LinkedHashMap<Integer, Account>();
        CheckingAccount checkingAccount = new CheckingAccount(1, "홍길동", 0);
        TimeDepositAccount timeDepositAccount = new TimeDepositAccount(2, "홍길동", 50_000_000, 0);
        OverdraftAccount overdraftAccount = new OverdraftAccount(3, "홍길동", 0);

        accounts.put(1, checkingAccount);
        accounts.put(2, timeDepositAccount);
        accounts.put(3, overdraftAccount);

        while (true) {
            System.out.print(">> 통장을 선택하세요(");
            Iterator<Integer> iterator = accounts.keySet().iterator();
            StringBuilder output = new StringBuilder();

            while (iterator.hasNext()) {
                int accountNumber = iterator.next();
                output.append(String.format("%d: %s", accountNumber, accounts.get(accountNumber).getAccountName()));
                if (iterator.hasNext()) {
                    output.append(", ");
                }
            }
            output.append(") ");
            System.out.print(output);
            String accountChoice = sc.nextLine();
            // 뒤로가기
            if (accountChoice.isBlank() || accountChoice.equals("0")) {
                System.out.println("금일 OneHanaBank는 업무를 종료합니다. 감사합니다.");
                break;
                // 통장 선택
            } else if (accountChoice.matches("\\d+")) {
                Account account = accounts.get(Integer.parseInt(accountChoice));  // 어떤 통장을 선택했는지
                if (account == null) continue;
                System.out.println(account);
                A: while (true) {
                    if (account instanceof TimeDepositAccount) {
                        System.out.print("> 정기예금이 만기되었습니다. (+: 만기처리, -: 출금, T: 이체, I: 정보) ");
                    } else {
                        System.out.print("> 원하시는 업무는? (+: 입금, -: 출금, T: 이체, I: 정보) ");
                    }
                    String businessChoice = sc.nextLine();
                    // 뒤로가기
                    if (businessChoice.isBlank() || businessChoice.equals("0")) {
                        break;
                        // 입금
                    } else if (businessChoice.equals("+")) {
                        if (account instanceof TimeDepositAccount) {
                            while (true) {
                                System.out.print("예치 개월 수를 입력하세요? (1~60개월) ");
                                String monthChoice = sc.nextLine();

                                if (monthChoice.isBlank() || monthChoice.equals("0")) {
                                    break;
                                } else {
                                    if (monthChoice.matches("\\d+")) {
                                        int month = Integer.parseInt(monthChoice);
                                        System.out.printf("%d개월(적용금리 %s%%)로 만기 처리하시겠어요? (y/n) ", month, ((TimeDepositAccount) account).calculateInterestRate(month));
                                        String dueChoice = sc.nextLine();
                                        if (dueChoice.isBlank() || dueChoice.equals("0")) {
                                            break;
                                        } else if (dueChoice.equalsIgnoreCase("y")) {
                                            System.out.print("어디로 보낼까요? (");
                                            for (Map.Entry<Integer, Account> entry : accounts.entrySet()) {
                                                if (entry.getValue() == account) continue;
                                                System.out.printf("%d: %s ", entry.getValue().getAccountNumber(), entry.getValue().getAccountName());
                                            }
                                            System.out.print(") ");
                                            String toAccountChoice = sc.nextLine();  // 보낼 통장 선택
                                            if (toAccountChoice.isBlank() || toAccountChoice.equals("0")) {
                                                break;
                                            } else {
                                                if (toAccountChoice.matches("\\d+")) {
                                                    Account to = accounts.get(Integer.parseInt(toAccountChoice));
                                                    int depositProceeds = (int) ((TimeDepositAccount) account).calculateDepositProceeds(month);
                                                    to.deposit(depositProceeds);
                                                    System.out.printf("%s 통장은 해지되었습니다. 감사합니다.\n", account.getAccountName());
                                                    accounts.remove(account.getAccountNumber());
                                                    break A;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            while (true) {
                                System.out.print("입금하실 금액은? ");
                                String amountChoice = sc.nextLine();
                                if (amountChoice.isBlank() || amountChoice.equals("0")) {
                                    break;
                                }
                                if (amountChoice.matches("\\d+")) {
                                    int amount = Integer.parseInt(amountChoice);
                                    account.deposit(amount);
                                    break;
                                }
                            }
                        }
                        // 출금
                    } else if (businessChoice.equals("-")) {
                        while (true) {
                            try {
                                account.withdraw(0);
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                                break;
                            }
                            System.out.print("출금하실 금액은? ");
                            String amountChoice = sc.nextLine();
                            if (amountChoice.isBlank() || amountChoice.equals("0")) {
                                break;
                            }
                            if (amountChoice.matches("\\d+")) {
                                try {
                                    int amount = Integer.parseInt(amountChoice);
                                    account.withdraw(amount);
                                    System.out.printf("%s 통장에서 %s원이 출금되었습니다.\n", account.getAccountName(), df.format(amount));
                                    System.out.printf("%s 통장의 잔액은 %s원입니다.\n", account.getAccountName(), df.format(account.getBalance()));
                                    break;
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                        }
                    } else if (businessChoice.equals("T")) {
                        B: while (true) {
                            try {
                                account.transfer(0, account);
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                                break;
                            }
                            System.out.print("어디로 보낼까요? (");
                            for (Map.Entry<Integer, Account> entry : accounts.entrySet()) {
                                if (entry.getValue() == account) continue;
                                System.out.printf("%d: %s ", entry.getValue().getAccountNumber(), entry.getValue().getAccountName());
                            }
                            System.out.print(") ");
                            String toAccountChoice = sc.nextLine();  // 보낼 통장 선택
                            if (toAccountChoice.isBlank() || toAccountChoice.equals("0")) {
                                break;
                            } else {
                                if (toAccountChoice.matches("\\d+")) {
                                    while (true) {
                                        Account to = accounts.get(Integer.parseInt(toAccountChoice));
                                        System.out.printf("%s 통장에 보낼 금액은? ", to.getAccountName());
                                        String amountChoice = sc.nextLine();
                                        if (amountChoice.isBlank() || amountChoice.equals("0")) {
                                            break;
                                        }
                                        if (amountChoice.matches("\\d+")) {
                                            try {
                                                int amount = Integer.parseInt(amountChoice);
                                                account.transfer(amount, to);
                                                System.out.printf("%s 통장에 %s원이 입금되었습니다.\n", to.getAccountName(), df.format(amount));
                                                System.out.printf("%s 통장의 잔액은 %s원입니다.\n", account.getAccountName(), df.format(account.getBalance()));
                                                break B;
                                            } catch (Exception e) {
                                                System.out.println(e.getMessage());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else if (businessChoice.equals("I")) {
                        System.out.println(account);
                        if (account instanceof TimeDepositAccount) {
                            System.out.println("* 예치 개월에 따른 적용 금리");
                            System.out.println("\t1개월 이상\t3.0%");
                            System.out.println("\t3개월 이상\t3.35%");
                            System.out.println("\t6개월 이상\t3.4%");
                            System.out.println("\t9개월 이상\t3.35%");
                            System.out.println("\t12개월 이상\t3.35%");
                            System.out.println("\t24개월 이상\t2.9%");
                            System.out.println("\t36개월 이상\t2.9%");
                            System.out.println("\t48개월 이상\t2.9%");
                        }
                    }
                }
            }
        }
    }
}
