package com.company.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpression {

    public static void main(String[] args) {

    }

    public static void checkingUserData(Map<String, String> request) {
        Map<String, String> regMap = new HashMap<>() {
            {
                put("firstname", "@^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$@gm");
                put("lastname", "@^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$@gm");
                put("DateOfBirthday", "([1-31]{1,2}).([1-12]{1,2}).([1950-2050]{4,4})");
                put("age", "^[0-9]{2,3}$");
                put("numberPassport", "\\d{4}\\s\\d{6}");
                put("numberPhone", "^(\\((\\+55)*\\d{2}\\)\\d{2}-\\d{3}-\\d{3})$");
                put("email", "/^[A-Z0-9._%+-]+@[A-Z0-9-]+.+.[A-Z]{2,4}$/i");
            }
        };

        for (Map.Entry<String, String> entry : request.entrySet()) {
            String regex = regMap.get(entry.getKey());
            Pattern patternName = Pattern.compile(regex);
            Matcher matcherName = patternName.matcher(entry.getValue());
            boolean matchesName = matcherName.matches();
            if (matchesName==true) {

            } else {
                System.out.println("не верные данные");;
            }


        }
    }
}
