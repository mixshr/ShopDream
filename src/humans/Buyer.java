package humans;

import products.Product;

import java.util.ArrayList;

public class Buyer extends Human {
    private ArrayList<Product> products;

    public Buyer(String name, String surName) {
        super(name, surName);
        this.products = new ArrayList<>();
    }

    // Получаем товарную корзину для пополнения
    public ArrayList<Product> getProducts() {
        return products;
    }
}