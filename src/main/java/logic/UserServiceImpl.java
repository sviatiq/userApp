package logic;

import domain.Role;
import domain.User;

import java.util.Map;
import java.util.Scanner;

public class UserServiceImpl implements UserService{

    private Scanner scanner = new Scanner(System.in);
    private ServiceClass serviceClass;
    @Override
    public User addUser() {
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();

        System.out.print("Введите фамилию: ");
        String surname = scanner.nextLine();

        System.out.print("Введите почту: ");
        String email = scanner.nextLine();
        email = checkMail(scanner, email);

        System.out.print("Введите роль: ");
        String role = scanner.nextLine();

        System.out.print("Введите телефон: ");
        String cellphone = scanner.nextLine();
        cellphone = checkCellphone(scanner, cellphone);

        return setUser(name, surname, email, role, cellphone);
    }
    private User setUser(String name, String surname, String email, String role, String cellphone) {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setRole(Role.valueOf(role.toUpperCase()));
        user.setCellphone(cellphone);
        return user;
    }
    @Override
    public User changeUser(User user) {
        String parametr;
        System.out.println("Edit: ");
        System.out.println("New name: ");
        String newName = scanner.nextLine();
        user.setName(newName);

        System.out.println("New surname: ");
        String newSurname = scanner.nextLine();
        user.setSurname(newSurname);

        System.out.println("New mail: ");
        parametr = scanner.nextLine();
        user.setEmail(parametr);

        System.out.println("New role: ");
        String role = scanner.nextLine();
        user.setRole(Role.valueOf(role.toUpperCase()));

        System.out.println("New cellphone: ");
        String cellphone = scanner.nextLine();
        user.setCellphone(cellphone);
        return user;
    }
    @Override
    public Map<String, User> deleteByEmail(String mail, Map<String, User> users) {
            if (users.containsKey(mail)) {
                users.remove(mail);
            } else {
                System.out.println("User is absent");
            }
            return users;
    }

    private String checkMail(Scanner scanner, String email) {
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

    private String checkCellphone(Scanner scanner, String cellphone) {
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
