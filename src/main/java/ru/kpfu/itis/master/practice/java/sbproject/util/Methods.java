package ru.kpfu.itis.master.practice.java.sbproject.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.master.practice.java.sbproject.entities.Files;
import ru.kpfu.itis.master.practice.java.sbproject.entities.User;
import ru.kpfu.itis.master.practice.java.sbproject.services.FilesServiceImpl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/*****
 * @author Igor Astafyev
 * September, 2019
 * Additional methods for application
 *****/

@Component
public class Methods {

    final private FilesServiceImpl filesService;

    public Methods(FilesServiceImpl filesService) {
        this.filesService = filesService;
    }

    /* Сохранение файла в папку с логином пользователя на сервере */
    public void addFileToLocalFolder(MultipartFile file, User user) throws IOException {
        String FOLDER_PATH = "filesStorage/";
        String FOLDER_FULL_PATH = "src/main/resources/static/" + FOLDER_PATH;
        String fileName = file.getOriginalFilename();
        String uniqFileName = DigestUtils.md5Hex(user.getEmail()) + ">>>" + fileName;
        String userFolder = user.getFio().replaceAll("\\s+", "");
        String fileFolderPath = FOLDER_FULL_PATH + userFolder;

        if (createNewFolderForUserFiles(fileFolderPath)) {
            // Сохранение файла в локальную папку на сервер
            saveNewFile(file, fileFolderPath);
            filesService.saveNewFileByUser(new Files(uniqFileName, FOLDER_PATH + userFolder + "/" + fileName, user));
        }

        String serverFolderPath = "target/classes/static/filesStorage";
        String serverFilePath = serverFolderPath + "/" + userFolder;

        // Созранение файла в папку сервера (target)
        createNewFolderForUserFiles(serverFolderPath);
        if (createNewFolderForUserFiles(serverFilePath)) {
            saveNewFile(file, serverFilePath);
        }
    }

    // Создание папки для хранения файлов отдельного пользователя
    private boolean createNewFolderForUserFiles(String path) {
        File folder = new File(path);

        if (!folder.exists()) {
            return folder.mkdir();
        } else {
            return true;
        }
    }

    // Проверка наименования файлов на уникальность для одного пользователя
    public String checkNewFilenameFromDB(List<Files> filesList, String filename) {
        for (Files file : filesList) {
            if (file.getName().equals(filename)) {
                return "error";
            }
        }
        return "ok";
    }

    // Считывание и сохранение нового файла
    private void saveNewFile(MultipartFile filename, String pathToSave) throws IOException {
        BufferedOutputStream bos;
        BufferedOutputStream bos_copy;

        byte[] fileBytes = filename.getBytes();
        bos = new BufferedOutputStream(new FileOutputStream(
                new File(pathToSave + "/" + filename.getOriginalFilename())));

        bos.write(fileBytes);
        bos_copy = new BufferedOutputStream(new FileOutputStream(
                new File(pathToSave + "/" + filename.getOriginalFilename())));
        bos_copy.write(fileBytes);
        bos.close();
    }

    /* Хэширование пароля, добавление солей */
    public String hashPass(String password) {
        password = "first_sold" + password;
        password = Arrays.toString(DigestUtils.sha256(password));
        password += "additional_sold";
        password = DigestUtils.md5Hex(password);
        return password;
    }
}