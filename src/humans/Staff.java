package humans;

import java.math.BigDecimal;

public class Staff extends Human {
    private StaffType staffType;
    private BigDecimal plan;

    // Создаем сотрудника
    public Staff(String name, String surName, StaffType staffType) {
        super(name, surName);
        this.staffType = staffType;
        this.plan = new BigDecimal("0");
    }

    public BigDecimal getPlan() {
        return plan;
    }

    public void setPlan(BigDecimal plan) {
        this.plan = plan;
    }
}