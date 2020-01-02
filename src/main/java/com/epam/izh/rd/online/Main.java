package com.epam.izh.rd.online;

import com.epam.izh.rd.online.service.SimpleBigNumbersService;
import com.epam.izh.rd.online.service.SimpleDateService;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

public class Main {
    public static void main(String[] args) {

        {/* SECTION FOR DEBUGGING OPERATIONS WITH NUMBERS */
            System.out.println("\n/* SECTION FOR DEBUGGING OPERATIONS WITH NUMBERS */\n");

            SimpleBigNumbersService numSer = new SimpleBigNumbersService();

            System.out.println("0.1 + 0.2 = " + 0.1 + 0.2);
            System.out.println(0.1 + 0.2 + " is 0.1 + 0.2");
            System.out.println("0.1 + 0.2 = " + (new BigDecimal("0.1").add(new BigDecimal("0.2"))));
            System.out.println("0.1/0.2 = " + (new BigDecimal("0.1").divide(new BigDecimal("0.2"), 2, BigDecimal.ROUND_HALF_UP)));
            System.out.println("BigDecimal(String): 100/3 = " + (new BigDecimal("100").divide(new BigDecimal("3"), 2, BigDecimal.ROUND_HALF_UP)));
            System.out.println("BigDecimal(int): 100/3 = " + (new BigDecimal(100).divide(new BigDecimal(3), 2, BigDecimal.ROUND_HALF_UP)));
            System.out.println("\n************************\n");
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
        }/* END OF DEBUGGING OPERATIONS WITH NUMBERS */


        {/* SECTION FOR DEBUGGING OPERATIONS WITH DATES */

            System.out.println("\n/* SECTION FOR DEBUGGING OPERATIONS WITH DATES */\n");

            SimpleDateService dateSer = new SimpleDateService();
            LocalDate theDate = LocalDate.now();
            LocalDate sonBirthDate = LocalDate.of(2009, 11, 03);


            System.out.println("The current date is: " + theDate + " (theDate)");
            System.out.println("\tcurrent day is: " + theDate.getDayOfMonth());
            System.out.println("\tcurrent month is: " + theDate.getMonthValue() + " (" + theDate.getMonth() + ")");
            System.out.println("\tcurrent year is: " + theDate.getYear());

            System.out.println();

            System.out.println("theDate.format(DateTimeFormatter.ofPattern(\"dd-MM-yyyy\")): " + theDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            System.out.println("Today is the following date: " + dateSer.parseDate(theDate));
            System.out.println("The birth date of son is: " + dateSer.parseDate(sonBirthDate));

            LocalDateTime dateTime;
            String dateTimeString = "1900-01-31 21:30";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            System.out.println("\nThe given string to be parsed is: \"" + dateTimeString + "\"");
            System.out.println("now trying to parse the above given string ...");

            dateTime = dateSer.parseString(dateTimeString);

            System.out.println("The date and time from the given string are as follows: " + dateTime);
            System.out.println("--> the date is: " + dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            System.out.println("--> the time is: " + dateTime.format(DateTimeFormatter.ofPattern("HH:mm")));

            System.out.println();

            formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

            System.out.println("The date to be converted into formated string is: " + sonBirthDate);
            System.out.println("\tdd.MM.yyyy: " + dateSer.convertToCustomFormat(sonBirthDate, formatter));

            formatter = DateTimeFormatter.ofPattern("dd-LLLL-yyyy");
            System.out.println("\tdd-LLLL-yyyy: " + dateSer.convertToCustomFormat(sonBirthDate, formatter));

            formatter = DateTimeFormatter.ofPattern("yyyy\\LLLL\\d");
            System.out.println("\tyyyy\\LLLL\\d: " + dateSer.convertToCustomFormat(sonBirthDate, formatter));

            System.out.println();

            int thisYear = Year.now().getValue();

            for (int delta = 0; delta < 15; delta++) {
                System.out.println("--> " + (thisYear + delta) + (Year.of(thisYear + delta).isLeap() ? " is a LEAP YEAR!" : ""));
            }

            System.out.println("\nThe next leap year is: " + dateSer.getNextLeapYear());

            System.out.println();

            DecimalFormat formatByPattern = new DecimalFormat("###,###");
            int year = 2020;
            for (int n = 0; n < 5; n++) {
                System.out.println("The total length of the year " + (year + n) + " is --> " + formatByPattern.format(dateSer.getSecondsInYear(year + n)) + " seconds" + (Year.of(year + n).isLeap() ? " (a leap year!)" : ""));
            }


            System.out.println("\n/* END OF DEBUGGING OPERATIONS WITH DATES */\n");

        }/* END OF DEBUGGING OPERATIONS WITH DATES */
    }
}
