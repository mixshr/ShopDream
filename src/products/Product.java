package products;

import java.math.BigDecimal;

public class Product extends Throwable {
    // Название товара
    private String name;

    // Тип товара
    private ProductType productType;

    // Цена товара
    private BigDecimal price;

    // Создаем товар
    public Product(String name, ProductType productType, BigDecimal price) {
        this.name = name;
        this.productType = productType;
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }
}