package problem1.bookstore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Book {
    private String isbn;
    private String title;
    private int price;
    private String author;
    private String description;
    private String genre;
    private Calendar date;

    // Book 클래스 생성자
    public Book(String isbn, String title, int price, String author, String description, String genre, String strDate) {
        // 문자열로 받는 날짜 데이터 전처리
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date d = sdf.parse(strDate);
            this.date = Calendar.getInstance();
            this.date.setTime(d);

        } catch (ParseException e) {
            System.out.println("날짜 입력은 yyyy/MM/dd 형식입니다.");
        }

        this.isbn = isbn;
        this.title = title;
        this.price = price;
        this.author = author;
        this.description = description;
        this.genre = genre;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date d = date.getTime();
        String formattedDate = sdf.format(d);
        return String.format("%s | %s | %s | %s | %s | %s | %s", isbn, title, price + "원", author, description, genre, formattedDate);
    }
}
