package problem1;

public class User {
    private String name;
    private String phoneNumber;
    private Cart cart;

    public User(String name, String phoneNumber) {
        this.name = name; this.phoneNumber = phoneNumber;
        this.cart = new Cart();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Cart getCart() { return cart; }

    @Override
    public String toString() {
        return String.format("User[name=%s, phoneNumber=%s]", name, phoneNumber);
    }

    public void printInfo() {
        System.out.printf("이름 %s 연락처 %s\n", name, phoneNumber);
    }
}
