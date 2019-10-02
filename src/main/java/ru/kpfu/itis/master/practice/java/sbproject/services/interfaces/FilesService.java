package ru.kpfu.itis.master.practice.java.sbproject.services.interfaces;

import ru.kpfu.itis.master.practice.java.sbproject.entities.Files;
import ru.kpfu.itis.master.practice.java.sbproject.entities.User;

import java.util.List;

public interface FilesService {

    void saveNewFileByUser(Files file);

    void deleteFileByUser(String filename, User user);

    List<Files> getAllFilesByUser(User user);
}
