import bank.Bank;
import humans.*;
import products.Product;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Магазин
        Shop shop = new Shop("Dream", new Staff("Анатолий", "Николаевич", StaffType.DIRECTOR));

        // Банк
        Bank  bank = new Bank("AlfaBank", new Staff("Алексей", "Петров",StaffType.BANK_MANAGER));

        // Получаем одидаемое количество покупателей в течении рабочего цикла (дня =))
        int numberOfVisitors = BasicOperations.getRandomNum();

        for (int i = 0; i < numberOfVisitors - 1; i++) {

            Product product;

            // В магазин заходит покупатель
            Buyer buyer = new Buyer("Иван", "Иванов");

            // Отдел который выбрал покупатель
            Department departmentForBuyers = shop.getRandomDepartment();

            // Если покупатель не выбрал отдел, то ушел с магазина
            if (departmentForBuyers == null) {
                continue;
            } else departmentForBuyers.setContBuyers(1);

            // Получаем менеджера отдела
            Staff manager = departmentForBuyers.getManager();

            // Получаем товары отдела
            ArrayList<Product> products = departmentForBuyers.getProducts();

            // Определить товар, который хочет купить покупатель
            try {
                product = products.get(BasicOperations.getRandomNum());
            } catch (IndexOutOfBoundsException e){
                // Если покупатель не находит товар, то просто уходит с магазина
                continue;
            };
            products.remove(product);

            // Проводим операцию продажи
            BigDecimal moneyToCashier = OperationsForManagers.сarryOutSale(manager, buyer, product, bank);

            // Проводим операцию передачи денег кассиру
            if (moneyToCashier != null) {
                Staff cashier = shop.getCashier();
                cashier.setPlan(cashier.getPlan().add(moneyToCashier));
                buyer.setMoney(buyer.getMoney().subtract(moneyToCashier));
            } else products.add(product);
        }

        // Отчет о конце рабочего дня и премировании сотрудников
        System.out.println(shop.getFullReport(numberOfVisitors));

        // Банковский отчет
        System.out.println(bank.getReport());
    }
}