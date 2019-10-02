package ru.kpfu.itis.master.practice.java.sbproject.services.interfaces;

import ru.kpfu.itis.master.practice.java.sbproject.entities.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void saveNewUser(User user);

    User getUserById(Long id);

    User findUserByEmail(String email);

    boolean getUserByEmailAndAndPassword(String email, String password);
}
