package ru.kpfu.itis.master.practice.java.sbproject.services;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.master.practice.java.sbproject.entities.Files;
import ru.kpfu.itis.master.practice.java.sbproject.entities.User;
import ru.kpfu.itis.master.practice.java.sbproject.repositories.FilesRepository;
import ru.kpfu.itis.master.practice.java.sbproject.services.interfaces.FilesService;

import java.util.List;

/*****
 * @author Igor Astafyev
 * September, 2019
 * Files Service
 *****/

@Service
public class FilesServiceImpl implements FilesService {

    private final FilesRepository filesRepository;

    public FilesServiceImpl(FilesRepository filesRepository) {
        this.filesRepository = filesRepository;
    }

    @Override
    @Transactional
    public void saveNewFileByUser(Files file) {
        filesRepository.saveAndFlush(file);
    }

    @Override
    @Transactional
    public void deleteFileByUser(String filename, User user) {
        filename = DigestUtils.md5Hex(user.getEmail()) + ">>>" + filename;

        filesRepository.delete(filesRepository.getFileByNameAndUser(filename, user));
    }

    @Override
    @Transactional
    public List<Files> getAllFilesByUser(User user) {
        return filesRepository.getAllFilesByUser(user);
    }
}
