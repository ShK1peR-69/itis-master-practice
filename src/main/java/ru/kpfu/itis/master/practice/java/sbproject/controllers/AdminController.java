package ru.kpfu.itis.master.practice.java.sbproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.master.practice.java.sbproject.entities.User;
import ru.kpfu.itis.master.practice.java.sbproject.services.FilesServiceImpl;
import ru.kpfu.itis.master.practice.java.sbproject.services.UserServiceImpl;

import java.util.List;

/*****
 * @author Igor Astafyev
 * September, 2019
 * Methods for users with ADMIN role
 *****/

@Controller
public class AdminController {

    private final UserServiceImpl userService;
    private final FilesServiceImpl filesService;

    public AdminController(UserServiceImpl userService, FilesServiceImpl filesService) {
        this.userService = userService;
        this.filesService = filesService;
    }

    // Отображение списка всех пользователей
    @RequestMapping(value = "/users_list", method = RequestMethod.GET)
    public String returnUsersListPage(ModelMap modelMap) {
        List<User> allUsers = userService.getAllUsers();

        modelMap.put("all_users", allUsers);
        return "users_list";
    }

    // Получение списка всех документов конкретного пользователя
    @RequestMapping(value = "/user_{id}_docs", method = RequestMethod.GET)
    public String returnUserDocsList(@PathVariable Long id, ModelMap modelMap) {
        User user = userService.getUserById(id);

        modelMap.put("cur_user", user);
        modelMap.put("user_files", filesService.getAllFilesByUser(user));
        return "adminpage";
    }

    // Удаление файла у выбранного пользователя
    @RequestMapping(value = "/delete-{user_id}-filename-{filename}", method = RequestMethod.GET)
    public String deleteFileByUser(@PathVariable long user_id, @PathVariable String filename) {
        User user = userService.getUserById(user_id);

        filesService.deleteFileByUser(filename, user);
        return "redirect:/user_" + user_id + "_docs";
    }
}
