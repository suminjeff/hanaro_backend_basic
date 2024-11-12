package problem1.bookstore;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Book, Integer> cartInfo = new HashMap<Book, Integer>();

    public int getTotalPrice() {
        int totalPrice = 0;
        for (Map.Entry<Book, Integer> entry: cartInfo.entrySet()) {
            totalPrice += entry.getKey().getPrice() * entry.getValue();
        }
        return totalPrice;
    }

    public void addToCart(Book book) {
        cartInfo.put(book, cartInfo.get(book) == null ? 1 : cartInfo.get(book) + 1);
    }

    public void deleteFromCart(Book book) {
        cartInfo.remove(book);
    }

    public void clearCart() {
        cartInfo.clear();
    }

    public void printInfo() {
        System.out.println("장바구니 상품 목록 :");
        System.out.println("---------------------------------------");
        System.out.println("도서ID\t\t|\t수량\t|\t합계");
        DecimalFormat df = new DecimalFormat("#,###");
        for (Map.Entry<Book, Integer> entry : cartInfo.entrySet()) {
            System.out.println(entry.getKey().getIsbn() + "\t|\t" + entry.getValue() + "\t|\t" + df.format((long) entry.getValue() * entry.getKey().getPrice())+"원");
        }
        System.out.println("---------------------------------------");
    }

    public void printInvoice() {
        printInfo();
        DecimalFormat df = new DecimalFormat("#,###");
        System.out.printf("\t\t\t\t\t총액 : %s원\n", df.format(getTotalPrice()));

    }
}
