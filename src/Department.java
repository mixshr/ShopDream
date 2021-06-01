import humans.BasicOperations;
import humans.OperationsForManagers;
import humans.Staff;
import products.Product;
import products.ProductType;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Department implements OperationsForManagers {
    // Наименование отдела
    private String name;

    // Тип товаров отдела
    private ProductType productType;

    // Сотрудник отдела
    private Staff manager;

    // Количество посетивших отдел покупателей
    private int contBuyers;

    // Товары отдела
    private ArrayList<Product> products;

    // План продаж отдела
    private BigDecimal planOfDepartment;

    // Создаем отдел и заполняем товарный склад
    public Department(String name, ProductType productType, Staff manager, BigDecimal planOfDepartment) {
        this.name = name;
        this.productType = productType;
        this.manager = manager;
        this.products = CreateNewProducts();
        this.planOfDepartment = planOfDepartment;
    }

    // Пополним товарный склад и сгенерируем названия и цены на товары
    private ArrayList<Product> CreateNewProducts() {
        ArrayList<Product> products =new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            products.add(new Product( productType.getName() + " № " + i, productType, BasicOperations.getRandomMoney(10000)));
        }
        return products;
    }

    public Staff getManager() {
        return manager;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public int getContBuyers() {
        return contBuyers;
    }

    public void setContBuyers(int count) {
        contBuyers += count;
    }

    public BigDecimal getPlanOfDepartment() {
        return planOfDepartment;
    }

    @Override
    public String getReport() {
        String planIsReady = (planOfDepartment.compareTo(manager.getPlan()) != -1) ? "отдел не выполнил поставленный план" : "отдел выполнил поставленный план" ;
        return "Отдел по продаже " + productType.getName() + "ов,\n" +
                "поставлен план " + planOfDepartment + "\n" +
                "посетило " + getContBuyers() + " посетилей\n" +
                "продал " + (99 - products.size()) + " товаров \n" +
                "на сумму " + manager.getPlan().toString() + "\n" +
                planIsReady + "\n" +
                "менеджер отдела: " + manager.getName();
    }
}