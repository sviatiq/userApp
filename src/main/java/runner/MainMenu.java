package runner;

import domain.User;
import logic.ServiceClass;
import logic.UserService;
import logic.UserServiceImpl;

import java.util.Scanner;

public class MainMenu {
    private UserService userService = new UserServiceImpl();
    private ServiceClass serviceClass = new ServiceClass();
    private Scanner scanner = new Scanner(System.in);

    public void startMenu() {
        String mail;
        boolean active = true;


        serviceClass.readFromFile();

        while (active) {
            System.out.println("LetsCode! Choose: ");
            System.out.println("/add -– Add user");
            System.out.println("/edit –– Edit user's info");
            System.out.println("/delete –- Delete user by email");
            System.out.println("/show –– Show all users");
            System.out.println("/exit –– Exit");
            String userChoose = scanner.nextLine();

            switch (userChoose) {
                case "/add":
                    User user = userService.addUser();
                    serviceClass.writeToFile(user);
                    break;
                case "/edit":
                    System.out.println("Type email, that must be edit: ");
                    mail = scanner.nextLine();
                    User user1 = serviceClass.getUsers().get(mail);
                    User newUser = userService.changeUser(user1);
                    serviceClass.getUsers().remove(mail);
                    serviceClass.getUsers().put(newUser.getEmail(), newUser);
                    serviceClass.rewriteFile(serviceClass.getUsers());
                    break;
                case "/delete":
                    System.out.println("Type email, that must be deleted: ");
                    mail = scanner.nextLine();
                    userService.deleteByEmail(mail, serviceClass.getUsers());
                    serviceClass.rewriteFile(serviceClass.getUsers());
                case "/show":
                    if (!serviceClass.getUsers().isEmpty()) {
                        serviceClass.showUserInformation(serviceClass.getUsers());
                    } else {
                        System.out.println("User's list is empty!");
                    }
                    break;
                case "/exit":
                    active = false;
                    break;
                default:
                    System.out.println("I don't know this command");
            }
        }
    }
}