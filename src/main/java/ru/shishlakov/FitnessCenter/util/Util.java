package ru.shishlakov.FitnessCenter.util;

import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

public class Util {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static String uploadPath;

    public static String getUploadPath() {
        return uploadPath;
    }

    public static void setUploadPath(String uploadPath) {
        Util.uploadPath = uploadPath;
    }

    public static String getString(String str) {
        return StringUtils.capitalize(str.trim());
    }

    public static LocalDate getDate(String str) {
        return LocalDate.parse(str, formatter);
    }

    public static String getPhone(String number) {
        String result = number.replaceAll("[\\D]", "");

        if (result.charAt(0) == '8') {
            result = result.replaceFirst("[8]", "7");
        } else if (result.length() == 10 && result.charAt(0) == '9') {
            result = 7 + result;
        }

        if (result.length() == 11 && result.startsWith("79")) {
            return result;
        } else {
            result = "Неверный формат номера";
        }

        return result;
    }

    public static char getSex(String sex) {
        return sex.trim().charAt(0);
    }

    public static Map<String, String> getPersonErrors(Map<String, String> map) {
        Map<String, String> errors = new HashMap<>();

        map.keySet().forEach(k -> {
            String temp = map.get(k);

            switch (k) {
                case "lastName":
                    if (temp.length() < 2)
                        errors.put(k + "Error", "Поле должно содержать не менее 2 символов");
                    else if (!CheckInput.checkLastName(temp)) {
                        errors.put(k + "Error", "Поле содержит недопустимые символы");
                    }
                    break;
                case "firstName":
                    if (temp.length() < 2)
                        errors.put(k + "Error", "Поле должно содержать не менее 2 символов");
                    else if (!CheckInput.checkFirstName(temp)) {
                        errors.put(k + "Error", "Поле содержит недопустимые символы");
                    }
                    break;
                case "middleName":
                    if (temp.length() < 2)
                        errors.put(k + "Error", "Поле должно содержать не менее 2 символов");
                    else if (!CheckInput.checkMiddleName(temp)) {
                        errors.put(k + "Error", "Поле содержит недопустимые символы");
                    }

                    break;
                case "birthday":
                case "startWork":
                    try {
                        LocalDate d = getDate(temp);

                        if (d.compareTo(LocalDate.now()) > 0 ||
                                d.getYear() < LocalDate.now().getYear() - 100) {
                            errors.put(k + "Error", "День рождения еще не наступил или был более 100 лет назад");
                        }
                    } catch (DateTimeParseException e) {
                        errors.put(k + "Error", "Введена некорректная дата");
                    }
                    break;
                case "phoneNumber":
                    if (!CheckInput.checkPhoneNumber(temp))
                        errors.put(k + "Error", "Введен некорректный номер телефона");
                    break;
            }
        });

        return errors;
    }

    public static Map<String, String> getUserErrors(Map<String, String> map) {
        Map<String, String> errors = new HashMap<>();

        map.keySet().forEach(k -> {
            String temp = map.get(k);

            switch (k) {
                case "username":
                    if (temp.length() < 4)
                        errors.put(k + "Error", "Поле должно содержать не менее 4 символов");
                    else if (!CheckInput.checkUsername(temp)) {
                        errors.put(k + "Error", "Поле содержит недопустимые символы");
                    }
                    break;
                case "password":
                    if (temp.length() < 5 || temp.length() > 10)
                        errors.put(k + "Error", "Поле должно содержать от 5 до 10 символов");
                    else if (!CheckInput.passwordPattern(temp)) {
                        errors.put(k + "Error", "Поле содержит недопустимые символы");
                    }
                    break;
                case "position":
                    if (temp.length() < 5)
                        errors.put(k + "Error", "Поле должно содержать не менее 5 символов");
                    else if (!CheckInput.checkPosition(temp)) {
                        errors.put(k + "Error", "Поле содержит недопустимые символы");
                    }
                    break;
            }
        });

        return errors;
    }
}
