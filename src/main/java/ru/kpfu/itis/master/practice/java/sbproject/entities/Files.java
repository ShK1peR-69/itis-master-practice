package ru.kpfu.itis.master.practice.java.sbproject.entities;

import javax.persistence.*;

/*****
 * @author Igor Astafyev
 * September, 2019
 * DB "files" table
 *****/

@Entity
@Table(name = "files")
public class Files {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "file_seq", sequenceName = "files_id_seq",
            initialValue = 3, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "file_seq")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "path")
    private String path;

    @ManyToOne
            (cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user_id;

    public Files() {
    }

    public Files(String name, String path, User user_id) {
        this.name = name;
        this.path = path;
        this.user_id = user_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name.split(">>>")[1];
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Files{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}
