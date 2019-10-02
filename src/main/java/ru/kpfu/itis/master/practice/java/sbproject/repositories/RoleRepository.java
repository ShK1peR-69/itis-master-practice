package ru.kpfu.itis.master.practice.java.sbproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.master.practice.java.sbproject.entities.User;

/*****
 * @author Igor Astafyev
 * September, 2019
 * Roles Repository using JPA
 *****/

@Repository
public interface RoleRepository extends JpaRepository<User, Long> {
}
