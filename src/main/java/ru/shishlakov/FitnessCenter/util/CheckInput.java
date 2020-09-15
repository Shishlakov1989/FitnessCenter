package ru.shishlakov.FitnessCenter.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckInput {
    private static Pattern lastNamePattern = Pattern.compile("^\\s*[а-яА-ЯёЁ\\s*]{2,}-*[а-яА-ЯёЁ\\s*]*$");
    private static Pattern firstNamePattern = Pattern.compile("^[а-яА-ЯёЁ\\s*]{2,}$");
    private static Pattern middleNamePattern = Pattern.compile("^[а-яА-ЯёЁ\\s+]{2,}-?\\s*[а-яА-ЯёЁ\\s*]*$");
    private static Pattern phoneNumberPattern = Pattern.compile("^\\s*\\+?[\\d-()\\s]{10,20}\\s*$");
    private static Pattern positionPattern = Pattern.compile("^[а-яА-ЯёЁ\\w\\d-\\s]{5,50}$");
    private static Pattern usernamePattern = Pattern.compile("^[\\w\\d]{4,15}$");
    private static Pattern passwordPattern = Pattern.compile("^[\\w\\d-_]{5,10}$");

    private static Matcher matcher;

    public static boolean checkLastName(String str) {
        str = str.trim();
        matcher = lastNamePattern.matcher(str);
        return matcher.matches();
    }

    public static boolean checkFirstName(String str) {
        matcher = firstNamePattern.matcher(str);
        return matcher.matches();
    }

    public static boolean checkMiddleName(String str) {
        matcher = middleNamePattern.matcher(str);
        return matcher.matches();
    }

    public static boolean checkPhoneNumber(String str) {
        matcher = phoneNumberPattern.matcher(str);
        String result = str.replaceAll("[\\D]", "");
        boolean b = false;

        switch (result.length()) {
            case 10:
                if (result.startsWith("9"))
                    b = true;
                break;
            case 11:
                if (result.startsWith("79") || result.startsWith("89"))
                    b = true;
        }

        return matcher.matches() && b;
    }

    public static boolean checkPosition(String str) {
        matcher = positionPattern.matcher(str);
        return matcher.matches();
    }

    public static boolean checkUsername(String str) {
        matcher = usernamePattern.matcher(str);
        return matcher.matches();
    }

    public static boolean passwordPattern(String str) {
        matcher = passwordPattern.matcher(str);
        return matcher.matches();
    }
}
