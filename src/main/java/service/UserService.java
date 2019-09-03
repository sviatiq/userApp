package service;

import domain.User;

import java.util.Map;

public interface UserService {
    User addUser();

    User changeUser(User user);

    Map<String, User> deleteByEmail(String mail, Map<String, User> list);
}
