package ru.kpfu.itis.master.practice.java.sbproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.master.practice.java.sbproject.entities.Files;
import ru.kpfu.itis.master.practice.java.sbproject.entities.User;
import ru.kpfu.itis.master.practice.java.sbproject.services.FilesServiceImpl;
import ru.kpfu.itis.master.practice.java.sbproject.services.UserServiceImpl;
import ru.kpfu.itis.master.practice.java.sbproject.util.Methods;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/*****
 * @author Igor Astafyev
 * September, 2019
 * Methods for user profile work
 *****/

@Controller
public class ProfileController {

    private final FilesServiceImpl filesService;
    private final UserServiceImpl userService;
    private final Methods methods;
    private final HttpServletRequest request;

    public ProfileController(FilesServiceImpl filesService, UserServiceImpl userService,
                             Methods methods, HttpServletRequest request) {
        this.filesService = filesService;
        this.userService = userService;
        this.methods = methods;
        this.request = request;
    }

    // Возвращает страницу профиля пользователя
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String returnProfilePage(ModelMap model) {
        //model.put("user", user);
        //model.put("files", filesList);
        return "profile";
    }

    // Сохранение файла пользователем, загрузка его на сервер и добавление в БД
    @RequestMapping(value = "/download-file-{user_id}", method = RequestMethod.POST)
    public String downloadNewFile(
            @RequestParam(value = "file") MultipartFile file,
            @PathVariable long user_id) throws IOException {
        User user = userService.getUserById(user_id);
        HttpSession session = request.getSession();

        if (!file.isEmpty()) {
            methods.addFileToLocalFolder(file, user);
        }
        session.setAttribute("user", user);
        session.setAttribute("files", filesService.getAllFilesByUser(user));
        return "redirect:/profile";
    }

    // Проверка имени загружаемого файла на уникальность
    // AJAX-запрос приходит из файла 'checkData.js'
    @RequestMapping(value = "/check-file", method = RequestMethod.POST)
    @ResponseBody
    public String checkNewFilename(
            @RequestParam(value = "filename") String filename,
            @RequestParam(value = "user_id") long user_id) {
        User user = userService.getUserById(user_id);
        List<Files> filesList = filesService.getAllFilesByUser(user);

        return methods.checkNewFilenameFromDB(filesList, filename);
    }

    // Удаление пользователем файла из своего профиля
    @RequestMapping(value = "/delete-{user_id}-file-{filename}", method = RequestMethod.GET)
    public String deleteFileByUser(@PathVariable long user_id, @PathVariable String filename) {
        User user = userService.getUserById(user_id);
        HttpSession session = request.getSession();

        filesService.deleteFileByUser(filename, user);
        session.setAttribute("user", user);
        session.setAttribute("files", filesService.getAllFilesByUser(user));
        return "redirect:/profile";
    }
}
