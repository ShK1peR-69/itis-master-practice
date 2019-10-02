package ru.kpfu.itis.master.practice.java.sbproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kpfu.itis.master.practice.java.sbproject.entities.User;
import ru.kpfu.itis.master.practice.java.sbproject.services.FilesServiceImpl;
import ru.kpfu.itis.master.practice.java.sbproject.services.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/*****
 * @author Igor Astafyev
 * September, 2019
 * Class for registration new users
 *****/

@Controller
public class RegistrationController {

    private HttpServletRequest request;
    private final UserServiceImpl userService;
    private final FilesServiceImpl filesService;

    public RegistrationController(HttpServletRequest request, UserServiceImpl userService,
                                  FilesServiceImpl filesService) {
        this.request = request;
        this.userService = userService;
        this.filesService = filesService;
    }

    // Регистрация нового пользователя в системе, сохранение данных
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String newUserRegistration(@RequestParam("fio") String fio,
                                      @RequestParam("email") String email,
                                      @RequestParam("pass_one") String first_pass,
                                      @RequestParam("pass_two") String second_pass,
                                      ModelMap model) {
        HttpSession session = request.getSession();

        // Дополнительная проверка на сервере того, что введенные пароли совпадают друг с другом
        if (!first_pass.equals(second_pass)) {
            model.put("error_msg", "пароли не совпадают");
            return "/";
        }

        User user = new User(email, fio, first_pass);
        userService.saveNewUser(user);
        session.setAttribute("user", user);
        session.setAttribute("files", filesService.getAllFilesByUser(user));

        String login_url = "j_spring_security_check?j_username=" + email + "&j_password=" + second_pass;
        return "redirect:/profile";
    }

    //Проверка унивкальности введенного e-mail с помощью AJAX-запроса (приходит из файла 'checkData.js')
    @ResponseBody
    @RequestMapping(value = "/check-mail", method = RequestMethod.POST)
    public String checkNewUserEmail(@RequestParam("email") String email) {
        List<User> users = userService.getAllUsers();
        for (User u : users) {
            if (u.getEmail().equals(email)) {
                return "error";
            }
        }
        return "ok";
    }

}
