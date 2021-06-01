import humans.BasicOperations;
import humans.OperationsForManagers;
import humans.Staff;
import humans.StaffType;
import products.ProductType;

import java.math.BigDecimal;

public class Shop {
    // Какое название у магазина?
    private String name;

    // Отделения магазина
    private Department computerDepartment;
    private Department mobileDepartment;
    private Department accessoriesDepartment;

    // Руководитель магазина, ставлю final потому что дируктор может быть только один
    private Staff director;

    // Кассир магазина
    private Staff cashier;

    // Задаем план магазина
    private BigDecimal planOfShop;

    // Открываем магазин
    public Shop(String name, Staff director) {
        this.name = name;
        this.director = director;
        this.cashier = new Staff("Майкл", "Джексон", StaffType.CASHIER);

        computerDepartment = new Department("Department of computer",
                ProductType.COMPUTER, new Staff("Петр", "Петров", StaffType.MANAGER), BasicOperations.getRandomMoney(100000));

        mobileDepartment = new Department("Department of computer",
                ProductType.MOBILE_PHONE, new Staff("Марья", "Ивановна", StaffType.MANAGER), BasicOperations.getRandomMoney(100000));

        accessoriesDepartment = new Department("Department of computer",
                ProductType.ACCESSORIES, new Staff("Алексей", "Иванов", StaffType.MANAGER), BasicOperations.getRandomMoney(100000));
        planOfShop = getPlanAllDepartments();
    }

    private BigDecimal getPlanAllDepartments() {
        BigDecimal plan = new BigDecimal("0");
        plan = plan.add(computerDepartment.getPlanOfDepartment());
        plan = plan.add(mobileDepartment.getPlanOfDepartment());
        plan = plan.add(accessoriesDepartment.getPlanOfDepartment());
        return plan;
    }

    private BigDecimal getFactAllDepartments() {
        BigDecimal plan = new BigDecimal("0");
        plan = plan.add(computerDepartment.getManager().getPlan());
        plan = plan.add(mobileDepartment.getManager().getPlan());
        plan = plan.add(accessoriesDepartment.getManager().getPlan());
        return plan;
    }

    public Department getRandomDepartment() {
        int rand = 1 + (int) (Math.random() * 4);

        if (rand == 1) {
            return computerDepartment;
        } else if (rand == 2) {
            return mobileDepartment;
        } else if (rand == 3) {
            return accessoriesDepartment;
        } else return null;
    }

    public Staff getCashier() {
        return cashier;
    }

    public String getFullReport(int numberOfVisitors) {
        BigDecimal fact = getFactAllDepartments();
        String planIsready = (fact.compareTo(planOfShop) != -1) ? "магазин выполнил план продаж" : "магазин не выполнил план продаж";
        return "В магазин " + name + "\n" +
                "поставлен план: " + planOfShop + "\n" +
                "выполнен факт: " + fact + "\n" +
                "*****************************" + "\n" +
                "за день посетило " + numberOfVisitors + " покупателей " + "\n" +
                "*****************************" + "\n" +
                computerDepartment.getReport() + "\n" +
                "*****************************" + "\n" +
                mobileDepartment.getReport() + "\n" +
                "*****************************" + "\n" +
                accessoriesDepartment.getReport() + "\n" +
                "*****************************" + "\n" +
                planIsready + "\n" +
                "*****************************" + "\n" +
                rewardEmployees(computerDepartment) + "\n" +
                "*****************************" + "\n" +
                rewardEmployees(mobileDepartment) + "\n" +
                "*****************************" + "\n" +
                rewardEmployees(accessoriesDepartment) + "\n" +
                "*****************************";
    }

    public String rewardEmployees(Department department) {
        Staff manager = department.getManager();
        BigDecimal planManager = manager.getPlan();
        if (OperationsForManagers.planIsReady(planManager, department.getPlanOfDepartment())) {
            BigDecimal reward = planManager.multiply(new BigDecimal("0.20"));
            return "Менеджеру " + manager.getName() + " начислено бонусов " + reward;
        } else {
            return "Менеджеру " + manager.getName() + " не начислено бонусов";
        }
    }
}