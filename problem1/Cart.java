package problem1;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Book, Integer> cartInfo = new HashMap<Book, Integer>();

    public void addToCart(Book book) {
        cartInfo.put(book, cartInfo.get(book) == null ? 0 : cartInfo.get(book) + 1);
    }

    public String printInfo() {
        System.out.println("장바구니 상품 목록 :");
        System.out.println("---------------------------------------");


        System.out.println("---------------------------------------");
    }
}
