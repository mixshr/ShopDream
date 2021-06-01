package humans;

import bank.Bank;
import products.Product;

import java.math.BigDecimal;
import java.util.ArrayList;

public interface OperationsForManagers {

    // Метод выдачи отчетов в конце рабочего дня
    String getReport();

    static Boolean planIsReady(BigDecimal fact, BigDecimal plan) {
      return fact.compareTo(plan) != -1;
    }

    // Определяем метод для совершения продажи
    static BigDecimal сarryOutSale(Staff manager, Buyer buyer, Product product, Bank bank) {
        // Проводим операцию купли продажи, если нет денег, то идем за кредитом в банк
        BigDecimal moneyOfBuyer = buyer.getMoney();
        BigDecimal priceOfProduct = product.getPrice();

        if(moneyOfBuyer.compareTo(priceOfProduct) >= 0) {
            ArrayList<Product> productsOfBuyers = buyer.getProducts();
            productsOfBuyers.add(product);

            // Менеджеру регистрируем продажу
            manager.setPlan(manager.getPlan().add(moneyOfBuyer));

            return priceOfProduct;
        } else { // Получаем кредит в банке
            BigDecimal loanCost = priceOfProduct.subtract(moneyOfBuyer);
            boolean approved = bank.getCredit(loanCost, buyer);
            if (approved) {
                return сarryOutSale(manager, buyer, product, bank);
            } else {
                return null;
            }
        }
    }
}