package service;

import domain.Role;
import domain.User;
import logic.ParamsValidator;

import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class UserServiceImpl extends ParamsValidator implements UserService {
    private Scanner scanner = new Scanner(System.in);
    private Set<Role> roles;

    @Override
    public User addUser() {
        System.out.print("Type a name: ");
        String name = scanner.nextLine();
        scanner.nextLine();
        System.out.print("Type a surname: ");
        String surname = scanner.nextLine();

        System.out.print("Typе a mail: ");
        String mail = scanner.nextLine();
        mail = checkMail(scanner, mail);

        setAllRoles();

        System.out.print("Type a phone: ");
        String cellphone = scanner.next();
        cellphone = checkCellphone(scanner, cellphone);

        return setUser(name, surname, mail, cellphone);
    }

    private void setAllRoles() {
        roles = new HashSet<>(3);
        System.out.println("How many roles must be? ");
        int count = scanner.nextInt();
        for (int i = 0; i < count; i++) {
            System.out.println("Input role: ");
            String role = scanner.next();
            if (roleExist(role))
                roles.add(Role.valueOf(role.toUpperCase()));
        }
    }

    @Override
    public User changeUser(User user){
        System.out.println("Edit: ");
        System.out.println("New name: ");
        String newName = scanner.nextLine();
        scanner.nextLine();
        System.out.println("New surname: ");
        String newSurname = scanner.nextLine();

        System.out.println("New mail: ");
        String newMail = scanner.nextLine();

        setAllRoles();

        System.out.println("New cellphone: ");
        String newCellphone = scanner.next();
        newCellphone = checkCellphone(scanner, newCellphone);

        return setUser(newName, newSurname, newMail, newCellphone);
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

    private User setUser(String name, String surname, String email, String cellphone) {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setRoles(roles);
        user.setCellphone(cellphone);
        return user;
    }

    private boolean roleExist(String role) {
        for (Role roles : Role.values())
            if (roles.name().equalsIgnoreCase(role))
                return true;
        return false;
    }

}
