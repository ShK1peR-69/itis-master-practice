package ru.kpfu.itis.master.practice.java.sbproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.master.practice.java.sbproject.entities.User;

import java.util.Optional;

/*****
 * @author Igor Astafyev
 * September, 2019
 * Users Repository using JPA
 *****/

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    Optional<User> findById(Long aLong);

    @Override
    User getOne(Long aLong);

    @Transactional
    @Query(value = "select * from users u where u.email = :email",
            nativeQuery = true)
    User findUserByEmail(@Param("email") String email);

    @Transactional
    @Query(value = "select * from users u where u.email = :email and u.password = :pass",
            nativeQuery = true)
    User getUserByEmailAndAndPassword(@Param("email") String email,
                     @Param("pass") String pass);
}
