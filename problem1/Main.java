package problem1;

import problem1.bookstore.Book;
import problem1.bookstore.User;

import java.text.SimpleDateFormat;
import java.util.*;


public class Main {
    public static void main (String[] args) {
        // 프로그램이 실행되면, 보유도서 정보를 적당한 자료구조에 저장
        Map<String, Book> books = new HashMap<String, Book>();
        Book book1 = new Book("ISBN1234", "셜록홈즈", 20000, "코난도일", "그 누구도 뛰어넘지 못했던 추리 소설의 고전", "추리소설", "2018/10/08");
        Book book2 = new Book("ISBN2345", "도리안 그레이의 초상", 16000, "오스카 와일드", "예술을 위한 예술!", "고전소설", "2022/01/22");
        Book book3 = new Book("ISBN3456", "쥐덫", 27000, "애거서크리스티", "폭설 속에 갇힌 몽스웰 여관 - 네 명의 손님과 주인 부부, 그리고 한 명의 형사", "추리소설", "2019/06/10");

        books.put("ISBN1234", book1);
        books.put("ISBN2345", book2);
        books.put("ISBN3456", book3);

        // 사용자 정보(이름, 연락처)를 입력 받음 (1. 고객 정보 확인하기 또는 6. 영수증 표시하기에서 사용)
        Scanner sc = new Scanner(System.in);
        System.out.print("당신의 이름을 입력하세요 : ");
        String name = sc.next();
        System.out.print("연락처를 입력하세요 : ");
        String phoneNumber = sc.next();
        User user = new User(name, phoneNumber);

        // 프로그램은 총 7개의 메뉴를 가지며, 프로그램이 종료될 때까지 인사말과 메뉴를 반복적으로 출력한다
        // 1번, 7번을 제외한 메뉴(2번~6번)는 해당 기능을 수행하는 별도의 메소드를 정의하여, main()메소드에서 호출하는 방식으로 처리한다
        while (true) {
            System.out.println("******************************************");
            System.out.println("오늘의 선택, 코난문고");
            System.out.println("영원한 스테디셀러, 명탐정 코난시리즈를 만나보세요~");
            System.out.println("******************************************");
            System.out.println("1. 고객 정보 확인하기 2. 장바구니 상품 목록 보기");
            System.out.println("3. 바구니에 항목 추가하기 4. 장바구니의 항목 삭제하기");
            System.out.println("5. 장바구니 비우기 6. 영수증 표시하기 7. 종료");
            System.out.println("******************************************");
            System.out.print("메뉴 번호를 선택해주세요 ");
            int menuNumber = sc.nextInt();
            if (menuNumber == 1) {
                System.out.println("현재 고객 정보 :");
                user.printInfo();
            } else if (menuNumber == 2) {
                user.getCart().printInfo();
            } else if (menuNumber == 3) {
                // 도서를 선택할 수 있도록 보유중인 도서목록을 출력
                for (Map.Entry<String, Book> entry : books.entrySet()) {
                    System.out.println(entry.getValue().toString());
                }

                System.out.print("장바구니에 추가할 도서의 ID를 입력하세요 : ");
                String isbn = sc.next();
                System.out.println("장바구니에 추가하겠습니까? Y | N");
                String choice = sc.next();
                // 대소문자 구분 없이 장바구니에 추가 (Y: yes, N: no)
                if (choice.equalsIgnoreCase("y")) {
                    user.getCart().addToCart(books.get(isbn));
                }
            } else if (menuNumber == 4) {
                user.getCart().printInfo();
                System.out.print("장바구니에서 삭제할 도서의 ID를 입력하세요 : ");
                String isbn = sc.next();
                user.getCart().deleteFromCart(books.get(isbn));
            } else if (menuNumber == 5) {
                // 5번의 경우 장바구니에 담긴 모든 아이템을 삭제함. 2번 메뉴를 통해 확인 가능
                user.getCart().clearCart();
                System.out.println("장바구니를 비웠습니다.");
            } else if (menuNumber == 6) {
                System.out.print("배송받을 분은 고객정보와 같습니까? ");
                String choice = sc.next();
                // 대소문자 구분 없이 장바구니에 추가 (Y: yes, N: no)
                if (choice.equalsIgnoreCase("y")) {
                    System.out.print("배송지를 입력해주세요 ");
                    String address = sc.next();
                    System.out.println("----------배송 받을 고객 정보----------");
                    user.printInfo();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date d = new Date();
                    String formattedDate = sdf.format(d);
                    System.out.printf("배송지 : %s 발송일 : %s\n", address, formattedDate);
                    user.getCart().printInfo();
                }
            } else if (menuNumber == 7) {
                break;
            };

        }
    }
}
