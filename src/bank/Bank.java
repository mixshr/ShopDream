package bank;

import humans.Buyer;
import humans.OperationsForManagers;
import humans.Staff;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Bank implements OperationsForManagers {
    private String name;
    private HashMap<Buyer, BigDecimal> clients;
    private BigDecimal moneyOfBank;
    private Staff manager;
    private BigDecimal limit;

    int numberOfVisitors;
    int numberOfDenied;

    public Bank(String name, Staff manager) {
        this.name = name;
        this.clients = new HashMap<>();
        this.moneyOfBank = new BigDecimal("1000000");
        this.limit = new BigDecimal("5000");
        this.manager = manager;
    }

    private void setMoneyOfBank(BigDecimal moneyOfBank) {
        this.moneyOfBank = moneyOfBank;
    }

    public boolean getCredit(BigDecimal loanCost, Buyer buyer) {
        numberOfVisitors += 1;
        if (limit.compareTo(loanCost) != -1) {
            setMoneyOfBank(moneyOfBank.subtract(loanCost));
            clients.put(buyer, loanCost);
            buyer.setMoney(buyer.getMoney().add(loanCost));
            manager.setPlan(manager.getPlan().add(loanCost));
            return true;
        } else {
            numberOfDenied += 1;
            return false;
        }
    }

    @Override
    public String getReport() {
        String strClients = "";
        BigDecimal sum = new BigDecimal("0");
        if (clients.size() != 0) {
            for (Map.Entry<Buyer, BigDecimal> entry : clients.entrySet()) {
                strClients += " клиент: " + entry.getKey().getName() + ", взял кредит: " + entry.getValue().toString() + "\n";
                sum = sum.add(entry.getValue());
            }
            strClients = "Среди них: " + " \n" + strClients +
                         "общая сумма выданных кредитов: " + sum;
        }

        int count = (numberOfVisitors - numberOfDenied);

        return "Банковского менеджера " + manager.getName() + " посетило: " + numberOfVisitors + " \n" +
                "кредитов одобренно: " + count + " \n" +
                strClients + "\n";
    }
}