package humans;

import java.math.BigDecimal;

public interface BasicOperations {
    // Рандомизируем деньги
    static BigDecimal getRandomMoney(int range){
        return new BigDecimal(String.valueOf((int) (Math.random() * range)));
    }

    // Получаем рандомное число
    static int getRandomNum() {
        return (int)(Math.random() * 100);
    }
}

