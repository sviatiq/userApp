package logic;

import java.util.Scanner;

public class ParamsValidator {
    protected String checkMail(Scanner scanner, String email) {
        for (int k = 0; k <= email.length(); k++) {
            if (email.contains("@") && email.contains(".")) {
                continue;
            } else {
                System.out.println("Неверная почта!");
                System.out.print("Введите почту: ");
                email = scanner.nextLine();
            }
        }
        return email;
    }

    protected String checkCellphone(Scanner scanner, String cellphone) {
        for (int j = 0; j <= cellphone.length(); j++) {
            if (cellphone != null && cellphone.substring(0, 3).equals("380") && cellphone.length() == 12) {
                continue;
            } else {
                System.out.print("Введен неправильный номер!");
                System.out.print("Введите телефон: ");
                cellphone = scanner.nextLine();
            }
        }
        return cellphone;
    }


}
