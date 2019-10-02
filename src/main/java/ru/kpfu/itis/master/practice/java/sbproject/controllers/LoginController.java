package ru.kpfu.itis.master.practice.java.sbproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.master.practice.java.sbproject.entities.User;
import ru.kpfu.itis.master.practice.java.sbproject.services.FilesServiceImpl;
import ru.kpfu.itis.master.practice.java.sbproject.services.UserServiceImpl;
import ru.kpfu.itis.master.practice.java.sbproject.util.Methods;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/*****
 * @author Igor Astafyev
 * September, 2019
 * Return login page for Sign in
 *****/

@Controller
public class LoginController {

    private final Methods methods;
    private final HttpServletRequest request;
    private final UserServiceImpl userService;
    private final FilesServiceImpl filesService;

    public LoginController(Methods methods, UserServiceImpl userService,
                           HttpServletRequest request, FilesServiceImpl filesService) {
        this.methods = methods;
        this.userService = userService;
        this.request = request;
        this.filesService = filesService;
    }

    // Возврщает страницу с формой для входа
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String returnLoginPage() {
        return "login";
    }

    // Проверка введенных пользователем данных для входа
    // Если все ОК - переходим на страницу профиля, иначе - возвращаем ошибку
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String checkUserLoginData(@RequestParam("login-email") String email,
                                     @RequestParam("login-pass") String password,
                                     ModelMap modelMap) {
        boolean isUserDataOk = false;
        HttpSession session = request.getSession();
        password = methods.hashPass(password);
        User user = userService.findUserByEmail(email);

        if (user != null) {
            isUserDataOk = userService.getUserByEmailAndAndPassword(email, password);
        }

        if (isUserDataOk) {
            session.setAttribute("user", user);
            session.setAttribute("files", filesService.getAllFilesByUser(user));
            return "profile";
        }
        modelMap.put("msg", "error");
        return "login";
    }
}
