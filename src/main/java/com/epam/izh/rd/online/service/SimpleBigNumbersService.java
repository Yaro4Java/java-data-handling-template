package com.epam.izh.rd.online.service;

import java.math.BigDecimal;
import java.math.BigInteger;

public class SimpleBigNumbersService implements BigNumbersService {

    /**
     * Метод делит первое число на второе с заданной точностью
     * Например 1/3 с точностью 2 = 0.33
     * @param range точность
     * @return результат
     */
    @Override
    public BigDecimal getPrecisionNumber(int a, int b, int range) {

        if (b == 0) {
            System.out.println("Недопустимая операция: деление на ноль ( " + a + " / 0 )!");
            return null;
        } else {
            return new BigDecimal(a).divide(new BigDecimal(b), range, BigDecimal.ROUND_HALF_UP);
        }
    }

    /**
     * Метод находит простое число по номеру
     *
     * @param range номер числа, считая с числа 2
     * @return простое число
     */
    @Override
    public BigInteger getPrimaryNumber(int range) {

        for (int i = 2, n = 0; n <= range; i++) {

            BigInteger temp = new BigInteger(String.valueOf(i));

            if (temp.isProbablePrime(10)) {
                if (n++ == range) { return temp; }
            }
        }

        return null;
    }
}
