package ru.kpfu.itis.master.practice.java.sbproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.master.practice.java.sbproject.entities.Files;
import ru.kpfu.itis.master.practice.java.sbproject.entities.User;

import java.util.List;

/*****
 * @author Igor Astafyev
 * September, 2019
 * Files Repository using JPA
 *****/

@Repository
public interface FilesRepository extends JpaRepository<Files, Long> {

    @Modifying
    @Transactional
    @Query(value = "select * from files f  where f.user_id = :user_id",
            nativeQuery = true)
    List<Files> getAllFilesByUser(@Param("user_id") User user);

    @Transactional
    @Query(value = "select * from files f  where f.user_id = :user_id " +
            "and f.name = :filename",
            nativeQuery = true)
    Files getFileByNameAndUser(@Param("filename") String filename,
                               @Param("user_id") User user);
}
