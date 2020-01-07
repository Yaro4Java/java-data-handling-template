package com.epam.izh.rd.online;

import com.epam.izh.rd.online.repository.SimpleFileRepository;
import com.epam.izh.rd.online.service.SimpleBigNumbersService;
import com.epam.izh.rd.online.service.SimpleDateService;
import com.epam.izh.rd.online.service.SimpleRegExpService;
import com.epam.izh.rd.online.service.SimpleTextService;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


        {/* PLAYING WITH THE STRING POOL */

            System.out.println("\n/* PLAYING WITH THE STRING POOL */\n");


            String a = "some string value";
            String b = new String(a);

            System.out.println("String a = \"some string value\";");
            System.out.println("String b = new String(a); // Initialzing b by the same value as a (\"some string value\") through constructor");
            System.out.println("\t|");
            System.out.println("\t| (a.hashCode() == b.hashCode()) = " + (a.hashCode() == b.hashCode()) + " (hashCode=" + a.hashCode() + ")");
            System.out.println("\t| a.equals(b) = " + a.equals(b));
            System.out.println("\t| b.equals(a) = " + b.equals(a));
            System.out.println("\tV");
            System.out.println("(a == b) = " + (a == b) + " // Using different references for a and b!");

            System.out.println("\nConnecting b reference with the String pool ( b = b.intern() ) ...");
            b = b.intern();
            System.out.println("\t|");
            System.out.println("\t| (a.hashCode() == b.hashCode()) = " + (a.hashCode() == b.hashCode()) + " (hashCode=" + a.hashCode() + ")");
            System.out.println("\t| a.equals(b) = " + a.equals(b));
            System.out.println("\t| b.equals(a) = " + b.equals(a));
            System.out.println("\tV");
            System.out.println("Now b is a reference to String pool as well as a:\n(a == b) = " + (a == b) + " // Using same reference for a and b");

            System.out.println("\n/* END OF PLAYING WITH THE STRING POOL */\n");

        }/* END OF PLAYING WITH THE STRING POOL */


        {/* DEBUGGING OPERATIONS WITH FILES */

            System.out.println("\n/* DEBUGGING OPERATIONS WITH FILES */\n");

            SimpleFileRepository rep4Files = new SimpleFileRepository();

            String path = "testDirCountFiles";

            System.out.println("Root path is '" + path +"':\n");

            long totalFiles = rep4Files.countFilesInDirectory(path);
            System.out.println("Total number of files in the folder '" + path + "' is " + totalFiles);

            long totalDirs = rep4Files.countDirsInDirectory(path);
            System.out.println("Total number of directories and subdirectories in the folder '" + path + "' ( including the folder itself ) is " + totalDirs);

            String file = "my_file.txt";
            String dir = "my_dir(Yaro4Java)";

            System.out.println("\nTrying to create text file '" + file + "' in directory '" + dir + "' ...");



            System.out.println("File '" + file + "' was " + (rep4Files.createFile(dir, file) ? "successfully " : "not ") + "created.");

            System.out.println("\n/* END OF DEBUGGING OPERATIONS WITH FILES */\n");

        }/* END OF DEBUGGING OPERATIONS WITH FILES */


        {/* PLAYING WITH RESOURCES */

            System.out.println("\n/* PLAYING WITH RESOURCES */\n");

            System.out.println("Listing ClassLoader URLs:\n");
            // Listing ClassLoader URLs
            URL[] urls = ((URLClassLoader) ClassLoader.getSystemClassLoader()).getURLs();
            for (URL url : urls) {
                System.out.println("---> '" + url.getPath() + "'");
            }

            URL res = Main.class.getClassLoader().getResource("testDirCreateFile/newFile.txt");
            System.out.println("\nGot resource 'testDirCreateFile/newFile.txt'? - " + (res != null));

            // Getting resources
            URL resource = Main.class.getResource("/");
            URI resURI = null;

            try {
                resURI = resource.toURI(); // URI for resources
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            // Getting path to compiled resources folder (target\classes\...)
            Path targetPath = Paths.get(resURI);
            String targetDir = targetPath.toString();

            String projectDir = new File("").getAbsolutePath();

            System.out.println("\nProject folder is '" + projectDir + "'");
            System.out.println("Compiled resources folder is '" + targetDir + "'");

            System.out.println("\n/* END OF PLAYING WITH RESOURCES */\n");

        }/* END OF PLAYING WITH RESOURCES */


        {/* PLAYING WITH STRINGS */

            System.out.println("\n/* PLAYING WITH STRINGS */\n");

            String s = "String for test!";
            char[] arr = s.toCharArray();

            System.out.println("A string to be converted into char array arr[]: '" + s + "'");
            for (int i = 0; i < s.length(); i++) {
                System.out.println("--> arr[" + i + "] = '" + arr[i] + "'");
            }

            System.out.println();

            SimpleTextService textSer = new SimpleTextService();

            String ini = "Vabudibuda baabaabaabaabaabaabaabaabaa!!?    ";
            System.out.println("ini = '" + ini + "'");
            String sub = "ba";
            System.out.println("sub = '" + sub + "'");

            String result = textSer.removeString(ini, sub);

            System.out.println("-------------------------------------");
            System.out.println("Result = removeString(ini, sub) = '" + result + "'");

            System.out.println("Was ini string a question? - " + (textSer.isQuestionString(ini) ? "Yep" : "Nope"));

            ini = null;
            System.out.println("\nini = '" + ini + "'");
            System.out.println("Was ini string a question? - " + (textSer.isQuestionString(ini) ? "Yep" : "Nope"));

            result = textSer.concatenate("Smells", " ", "Like", " ", "Teen", " ", "Spirit");

            System.out.println("\nTrying to concatinate strings: 'Smells', ' ', 'Like', ' ', 'Teen', ' ', 'Spirit' ...");
            System.out.println("Concatinated result is '" + result + "'");

            System.out.println();

            ini = "This is a meander transformation";
            System.out.println("ini = '" + ini + "'");
            System.out.println("The meandered result = '" + textSer.toJumpCase(ini) + "'");

            System.out.println();

            ini = "lOl=а роза* упал!а: на; % <  лапу? >>> Азора.LoL";
            System.out.println("ini = '" + ini + "'");
            System.out.println("Is ini a palindrome? - " + (textSer.isPalindrome(ini) ? "Yep" : "Nope"));

            System.out.println("\n/* END OF PLAYING WITH STRINGS */\n");

        }/* END OF PLAYING WITH STRINGS */


        {/* PLAYING WITH REGULAR EXPRESSIONS */

            System.out.println("\n/* PLAYING WITH REGULAR EXPRESSIONS */\n");

            String content = "What a wonderful world! 12.345 Wow!!! That was really cool!"; // Variable to put in the data from the file

            // Regular expression to identify account number to be masked
            String regex;
            Pattern pattern;
            Matcher matcher;

            System.out.println("The string to be searched through: '" + content + "'");

            regex = "[wW][a-zA-Z]+";
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(content);
            System.out.println("\nThe regular expression pattern to be used for searching a substring: '" + regex + "'");

            while (matcher.find()) {
                System.out.println("--> found matched substring: '" + content.substring(matcher.start(), matcher.end()) + "'");
            }

            regex = "\\w+[wW]\\b";
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(content);
            System.out.println("\nThe regular expression pattern to be used for searching a substring: '" + regex + "'");

            while (matcher.find()) {
                System.out.println("--> found matched substring: '" + content.substring(matcher.start(), matcher.end()) + "'");
            }

            content = "The account number is 1234 5555 6785 9900 and that's that.";
            System.out.println("\n\nThe string to be searched through: '" + content + "'");
            regex = "\\d{4}";
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(content);
            System.out.println("\nThe regular expression pattern to be used for searching a substring: '" + regex + "'");

            while (matcher.find()) {
                System.out.println("--> found matched substring: '" + content.substring(matcher.start(), matcher.end()) + "'");
            }

            regex = "(\\d{4})\\s(\\d{4}\\s\\d{4})\\s(\\d{4})";
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(content);
            System.out.println("\nThe regular expression pattern to be used for searching a substring: '" + regex + "'");
            while (matcher.find()) {
                System.out.println("Replacing group 2: '" + content.replaceAll(matcher.group(2), "**** ****") + "'");
            }

            content = "в размере ${payment_amount} рублей. На счету осталось ${balance} рублей";
            regex = "\\$\\{([a-z_0-9]+)\\}";
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(content);
            System.out.println("\n\nThe string to be searched through: \"" + content + "\"");
            System.out.println("The regular expression pattern to be used for searching a substring: \"" + regex + "\"");
            while (matcher.find()) {
                String group = Pattern.quote(matcher.group());
                String group1 = matcher.group(1);
                System.out.println("\n\t| matcher.group(1) = " + group1);
                System.out.println("\t| String group = Pattern.quote(matcher.group()) = " + group);
                System.out.println("\t| (matcher.group(1).intern() == \"payment_amount\") = (\"" + matcher.group(1).intern() + "\" == \"payment_amount\") = " + (matcher.group(1).intern() == "payment_amount"));
                System.out.println("\t| (matcher.group(1).intern() == \"balance\") = (\"" + matcher.group(1).intern() + "\" == \"balance\") = " + (matcher.group(1).intern() == "balance"));
                if (matcher.group(1).intern() == "payment_amount") {
                    System.out.println("--> Matching substring is found. Replacing 'group': \"" + content.replaceAll(group, "[>>> FOR PAYMENT AMOUNT <<<]") + "\"");
                }
                if (matcher.group(1).intern() == "balance") {
                    System.out.println("--> Matching substring is found. Replacing 'group': \"" + content.replaceAll(group, "[>>> FOR BALANCE <<<]") + "\"");
                }
            }




            // Using methods of another class object to work with files
            SimpleRegExpService regExpSer = new SimpleRegExpService();

            System.out.println("\nData from file:\n\"" + regExpSer.maskSensitiveData() + "\"");
            System.out.println("\nData from file:\n\"" + regExpSer.replacePlaceholders(5, 1000000000) + "\"");


            System.out.println("\n/* END OF PLAYING WITH REGULAR EXPRESSIONS */\n");

        }/* END OF PLAYING WITH REGULAR EXPRESSIONS */
    }
}
