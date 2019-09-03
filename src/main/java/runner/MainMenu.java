package runner;

import domain.User;
import logic.WriterClass;
import service.UserService;
import service.UserServiceImpl;

import java.util.Scanner;

public class MainMenu {
    private UserService userService = new UserServiceImpl();
    private WriterClass writerClass = new WriterClass();
    private Scanner scanner = new Scanner(System.in);

    public void startMenu() {
        String mail;
        boolean active = true;

        writerClass.readFromFile();

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
                    writerClass.writeToFile(user);
                    break;
                case "/edit":
                    System.out.println("Type email, that must be edit: ");
                    mail = scanner.nextLine();
                    User user1 = writerClass.getUsers().get(mail);
                    User newUser = userService.changeUser(user1);
                    writerClass.getUsers().remove(mail);
                    writerClass.getUsers().put(newUser.getEmail(), newUser);
                    writerClass.rewriteFile(writerClass.getUsers());
                    break;
                case "/delete":
                    System.out.println("Type email, that must be deleted: ");
                    mail = scanner.nextLine();
                    userService.deleteByEmail(mail, writerClass.getUsers());
                    writerClass.rewriteFile(writerClass.getUsers());
                case "/show":
                    if (!writerClass.getUsers().isEmpty()) {
                        writerClass.showUserInformation(writerClass.getUsers());
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