package ru.kpfu.itis.master.practice.java.sbproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*****
 * @author Igor Astafyev
 * September, 2019
 * Return registration page
 *****/

@Controller
public class MainController {

    // Возвращает главную страницу, содержащую форму для регистрации
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String returnMainPage() {
        return "mainpage";
    }

}