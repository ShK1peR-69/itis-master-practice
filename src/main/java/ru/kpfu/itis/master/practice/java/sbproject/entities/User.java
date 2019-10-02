package ru.kpfu.itis.master.practice.java.sbproject.entities;

import javax.persistence.*;
import java.util.List;

/*****
 * @author Igor Astafyev
 * September, 2019
 * DB "users" table
 *****/

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "user_seq", sequenceName = "users_id_seq",
            initialValue = 10, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_seq")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "fio")
    private String fio;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private Long role;

    @OneToMany(cascade = CascadeType.REFRESH,
            mappedBy = "user_id")
    private List<Files> files;

    public User() {
    }

    public User(String email, String fio, String password, Long role) {
        this.email = email;
        this.fio = fio;
        this.password = password;
        this.role = role;
    }

    public User(String email, String fio, String password) {
        this.email = email;
        this.fio = fio;
        this.password = password;
    }

    public User(String email, String fio, String password, Long role, List<Files> files) {
        this.email = email;
        this.fio = fio;
        this.password = password;
        this.role = role;
        this.files = files;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }

    public List<Files> getFiles() {
        return files;
    }

    public void setFiles(List<Files> files) {
        this.files = files;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", fio='" + fio + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
