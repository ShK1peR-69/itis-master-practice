package ru.kpfu.itis.master.practice.java.sbproject.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.master.practice.java.sbproject.entities.User;
import ru.kpfu.itis.master.practice.java.sbproject.repositories.UserRepository;
import ru.kpfu.itis.master.practice.java.sbproject.services.interfaces.UserService;
import ru.kpfu.itis.master.practice.java.sbproject.util.Methods;

import java.util.List;

/*****
 * @author Igor Astafyev
 * September, 2019
 * Users Service
 *****/

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Methods additionalMethods;

    public UserServiceImpl(UserRepository userRepository, Methods additionalMethods) {
        this.userRepository = userRepository;
        this.additionalMethods = additionalMethods;
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void saveNewUser(User user) {
        user.setPassword(additionalMethods.hashPass(user.getPassword()));
        user.setRole((long) 2);
        userRepository.saveAndFlush(user);
    }

    @Override
    @Transactional
    public User getUserById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public boolean getUserByEmailAndAndPassword(String email, String password) {
        User user = userRepository.getUserByEmailAndAndPassword(email, password);
        return user != null;
    }
}
