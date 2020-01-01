package com.epam.izh.rd.online;

import com.epam.izh.rd.online.service.SimpleBigNumbersService;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {

        /* SECTION FOR DEBUGGING OPERATIONS WITH NUMBERS */
        System.out.println("\n/* SECTION FOR DEBUGGING OPERATIONS WITH NUMBERS */\n");

        SimpleBigNumbersService numSer = new SimpleBigNumbersService();
        System.out.println("0.1 + 0.2 = " + 0.1 + 0.2);
        System.out.println(0.1 + 0.2 + " is 0.1 + 0.2");
        System.out.println("0.1 + 0.2 = " + (new BigDecimal("0.1").add(new BigDecimal("0.2"))));
        System.out.println("0.1/0.2 = " + (new BigDecimal("0.1").divide(new BigDecimal("0.2"),2, BigDecimal.ROUND_HALF_UP)));
        System.out.println("100/3 = " + (new BigDecimal("100").divide(new BigDecimal("3"),2, BigDecimal.ROUND_HALF_UP)));
        System.out.println("\n************************");
        System.out.println("100 / 0 = " + numSer.getPrecisionNumber(100, 0, 2));
        System.out.println("1 / 3 = " + numSer.getPrecisionNumber(1, 3, 2));

        System.out.println("\n************************\n");

        for (int a = 1; a < 21; a++) {
            System.out.println(a + ((new BigInteger(Integer.toString(a)).isProbablePrime(10)) ? " is a prime number!" : ""));
        }

        System.out.println();

        System.out.println("Is 541 a prime? - " + (new BigInteger(String.valueOf(541)).isProbablePrime(10)));

        System.out.println();

        for (int a = 0; a < 5; a++) {
            System.out.println("prime #" + a + ": " + numSer.getPrimaryNumber(a));
        }

        System.out.println("-------------");
        System.out.println("prime #" + 100 + ": " + numSer.getPrimaryNumber(100));


        System.out.println("\n/* END OF DEBUGGING OPERATIONS WITH NUMBERS */");
        /* END OF DEBUGGING OPERATIONS WITH NUMBERS */
    }
}
