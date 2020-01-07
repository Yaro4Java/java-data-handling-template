package com.epam.izh.rd.online.service;

public class SimpleTextService implements TextService {

    /**
     * Реализовать функционал удаления строки из другой строки.
     *
     * Например для базовой строки "Hello, hello, hello, how low?" и строки для удаления ", he"
     * метод вернет "Hellollollo, how low?"
     *
     * @param base - базовая строка с текстом
     * @param remove - строка которую необходимо удалить
     */
    @Override
    public String removeString(String base, String remove) {
        if ((base == null) || (remove == null)) { return null; }
        return base.replace(remove,"");
    }

    /**
     * Реализовать функционал проверки на то, что строка заканчивается знаком вопроса.
     *
     * Например для строки "Hello, hello, hello, how low?" метод вернет true
     * Например для строки "Hello, hello, hello!" метод вернет false
     */
    @Override
    public boolean isQuestionString(String text) {
        if (text == null) { return false; }
        return text.trim().endsWith("?");
    }

    /**
     * Реализовать функционал соединения переданных строк.
     *
     * Например для параметров {"Smells", " ", "Like", " ", "Teen", " ", "Spirit"}
     * метод вернет "Smells Like Teen Spirit"
     */
    @Override
    public String concatenate(String... elements) {
        if (elements == null) { return null; }
        String concat = "";
        for (int n = 0; n < elements.length; n++) {
            concat += elements[n];
        }
        return concat;
    }

    /**
     * Реализовать функционал изменения регистра в вид лесенки.
     * Возвращаемый текст должен начинаться с прописного регистра.
     *
     * Например для строки "Load Up On Guns And Bring Your Friends"
     * метод вернет "lOaD Up oN GuNs aNd bRiNg yOuR FrIeNdS".
     */
    @Override
    public String toJumpCase(String text) {

        if (text == null) { return null; }

        String result = "";

        for (int n = 0; n < text.length(); n++) {

            String sub = text.substring(n, n + 1); // Taking character from each position as a substring

            if((n % 2) == 0) { result += sub.toLowerCase(); }
            else { result += sub.toUpperCase(); }
        }

        return result;
    }

    /**
     * Метод определяет, является ли строка палиндромом.
     *
     * Палиндром - строка, которая одинаково читается слева направо и справа налево.
     *
     * Например для строки "а роза упала на лапу Азора" вернется true, а для "я не палиндром" false
     */
    @Override
    public boolean isPalindrome(String string) {

        if (string == null) { return false; }

        String temp = string.toLowerCase(); // To ignore case while analysing the contents
        temp = temp.replaceAll("[^a-zа-я]", ""); // To ignore non alphabetical characters

        int len = temp.length();

        if (len == 0) { return false; }

        for (int n = 0; n < len - n; n++) {

            String sub1 = temp.substring(n, n + 1).intern(); // Reading from left
            String sub2 = temp.substring(len - n - 1, len - n).intern(); // Reading from right

            // Comparing two interned string variables
            if(sub1 != sub2) { return false; }
        }

        return true;
    }
}
