package problem3;

import problem3.accounts.Account;
import problem3.accounts.CheckingAccount;
import problem3.accounts.OverdraftAccount;
import problem3.accounts.TimeDepositAccount;

import java.text.DecimalFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("#,###");

        // 길동이의 계좌
        Map<Integer, Account> accounts = new LinkedHashMap<Integer, Account>();
        accounts.put(1, new CheckingAccount(1, "홍길동", 0, 0));
        accounts.put(2, new TimeDepositAccount(2, "홍길동", 50_000_000, 0, 48));
        accounts.put(3, new OverdraftAccount(3, "홍길동", 0));

        String input;
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

            System.out.print(output);
            System.out.print(") ");
            input = sc.nextLine();
            // 뒤로가기
            if (input.isBlank() || input.equals("0")) {
                System.out.println("금일 OneHanaBank는 업무를 종료합니다. 감사합니다.");
                break;
                // 통장 선택
            } else if (input.matches("\\d+")) {
                Account account = accounts.get(Integer.parseInt(input));  // 어떤 통장을 선택했는지
                if (account == null) continue;
                System.out.println(account);
                while (true) {
                    System.out.print("> 원하시는 업무는? (+: 입금, -: 출금, T: 이체, I: 정보) ");
                    input = sc.nextLine();
                    // 뒤로가기
                    if (input.isBlank() || input.equals("0")) {
                        break;
                        // 입금
                    } else if (input.equals("+")) {
                        while (true) {
                            System.out.print("입금하실 금액은? ");
                            input = sc.nextLine();
                            if (input.matches("\\d+")) {
                                int amount = Integer.parseInt(input);
                                account.deposit(amount);
                                System.out.printf("%s에 %s원이 입금되었습니다!\n", account.getAccountName(), df.format(amount));
                            }
                            break;
                        }
                        // 출금
                    } else if (input.equals("-")) {
                        while (true) {
                            System.out.print("출금하실 금액은? ");
                            input = sc.nextLine();
                            if (input.matches("\\d+")) {
                                int amount = Integer.parseInt(input);
                                try {
                                    account.withdraw(amount);
                                } catch (Exception e) {
                                    System.out.println("출금할 수 없는 통장입니다.");
                                }
                                break;
                            }
                        }
                    } else if (input.equals("T")) {
                        while (true) {
                            System.out.print("어디로 보낼까요? (");
                            for (Map.Entry<Integer, Account> entry : accounts.entrySet()) {
                                if (entry.getValue() == account) continue;
                                System.out.printf("%d: %s ", entry.getValue().getAccountNumber(), entry.getValue().getAccountName());
                            }
                            System.out.print(") ");
                            input = sc.nextLine();  // 통장 선택
                            if (input.isBlank() || input.equals("0")) {
                                break;
                            } else {
                                if (input.matches("\\d+")) {

                                    while (true) {
                                        Account to = accounts.get(Integer.parseInt(input));
                                        System.out.printf("%s 통장에 보낼 금액은? ", to.getAccountName());
                                        String input2 = sc.nextLine();
                                        if (input2.isBlank() || input2.equals("0")) {
                                            break;
                                        } else {
                                            try {
                                                account.transfer(Integer.parseInt(input2), to);
                                            } catch (Exception e) {
                                                System.out.println("이체할 수 없는 통장입니다.");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else if (input.equals("I")) {
                        System.out.println(account);
                    }
                }
            }
        }
    }
}
