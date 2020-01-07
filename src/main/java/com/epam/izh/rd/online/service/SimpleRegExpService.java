package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.repository.SimpleFileRepository;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleRegExpService implements RegExpService {

    /**
     * Метод должен читать файл sensitive_data.txt (из директории resources) и маскировать в нем конфиденциальную информацию.
     * Номер счета должен содержать только первые 4 и последние 4 цифры (1234 **** **** 5678). Метод должен содержать регулярное
     * выражение для поиска счета.
     *
     * @return обработанный текст
     */
    @Override
    public String maskSensitiveData() {

        // Using methods of another class object to work with files
        SimpleFileRepository rep4File = new SimpleFileRepository();

        String content = ""; // Variable to put in the data from the file

        // Reading data from file
        content = rep4File.readFileFromResources("sensitive_data.txt");

        // Regular expression to identify account numbers
        String regex = "\\d{4}\\s(\\d{4}\\s\\d{4})\\s\\d{4}"; // Group 1 is to be masked by asterisks

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);

        while (matcher.find()) {
            content = content.replaceAll(matcher.group(1), "**** ****");
        }

        return content;
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {

        // Using methods of another class object to work with files
        SimpleFileRepository rep4File = new SimpleFileRepository();

        String content = ""; // Variable to put in the data from the file

        // Reading data from file
        content = rep4File.readFileFromResources("sensitive_data.txt");

        // Regular expression to identify account numbers
        String regex = "\\$\\{([a-z_0-9]+)\\}"; // Group 0 is to be replaced, group 1 is for identification

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);

        while (matcher.find()) {
            String group = Pattern.quote(matcher.group()); // Quoting found substring with metacharacters
            if(matcher.group(1).intern() == "payment_amount") {
                content = content.replaceAll(group, String.valueOf((int)paymentAmount));
            }
            if(matcher.group(1).intern() == "balance") {
                content = content.replaceAll(group, String.valueOf((int)balance));
            }
        }

        return content;
    }
}
