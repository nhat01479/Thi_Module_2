package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.example.StudentManager.scanner;

public class ValidateUtils {
    private static  String REGEX;

    public static String inputChoice() {
        while (true) {
            try {
                String number = scanner.nextLine();
                if (number != null && !number.trim().isEmpty() && numberValidate(number)) {
                    return number;
                } else {
                    System.out.println("Sai định dạng, vui lòng nhập lại!");
                }
            } catch (Exception e) {
                System.out.println("Sai định dạng, vui lòng nhập lại!");
            }
        }
    }
    public static String inputId() {
        while (true) {
            try {
                String id = scanner.nextLine();
//                if (id != null && !id.trim().isEmpty() && id.matches("\\d*")) {
                if (id != null && !id.trim().isEmpty() && numberValidate(id)) {
                    int number = Integer.parseInt(id);
                    if (number > 0 && number < 1000) {
                        return id;
                    } else {
                        System.out.println("ID phải lớn hơn 0 và nhỏ hơn 1000, vui lòng nhập lại!");
                    }
                } else {
                    System.out.println("Sai định dạng, vui lòng nhập lại!");
                }
            } catch (Exception e) {
                System.out.println("Sai định dạng, vui lòng nhập lại!");
            }
        }
    }
    public static String inputAge() {
        while (true) {
            try {
                String age = scanner.nextLine();
//                if (id != null && !id.trim().isEmpty() && id.matches("\\d*")) {
                if (age != null && !age.trim().isEmpty() && numberValidate(age)) {
                    int num = Integer.parseInt(age);
                    if (num > 0 && num < 120) {
                        return age;
                    } else {
                        System.out.println("Tuổi phải lớn hơn 0 và nhỏ hơn 120, vui lòng nhập lại!");
                    }
                } else {
                    System.out.println("Sai định dạng, vui lòng nhập lại!");
                }
            } catch (Exception e) {
                System.out.println("Sai định dạng, vui lòng nhập lại!");
            }
        }
    }
    public static String inputScore() {
        while (true) {
            try {
                String score = scanner.nextLine();
//                if (id != null && !id.trim().isEmpty() && id.matches("\\d*")) {
                if (score != null && !score.trim().isEmpty() && score.matches("^(10|\\d(\\.\\d)?)$")) {
                    float sc = Float.parseFloat(score);
                    if (sc >= 0 && sc <= 10) {
                        return score;
                    } else {
                        System.out.println("Điểm phải >= 0 và <= 10, vui lòng nhập lại!");
                    }
                } else {
                    System.out.println("Điểm phải >= 0 và <= 10, vui lòng nhập lại!");
                }
            } catch (Exception e) {
                System.out.println("Sai định dạng, vui lòng nhập lại!");
            }
        }
    }
    public static boolean numberValidate(String number) {
        REGEX = "\\d+";
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }
}
