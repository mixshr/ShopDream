package humans;

import java.math.BigDecimal;

abstract class Human <T, G> {
    private String name;
    private String surName;
    private BigDecimal money;

    // Создаем человека с соответствующими полями
    public Human(String name, String surName) {
        this.name = name;
        this.surName = surName;
        this.money = BasicOperations.getRandomMoney(100000);
    }

    public String getName() {
        return name + " " + surName;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}