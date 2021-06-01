package products;

public enum ProductType {
    ACCESSORIES("аксессуар"),
    COMPUTER("компьютер"),
    MOBILE_PHONE("моб. телефон");

    private final String name;

    ProductType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}