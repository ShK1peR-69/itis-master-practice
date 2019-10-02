package ru.kpfu.itis.master.practice.java.sbproject.entities;

import javax.persistence.*;

/*****
 * @author Igor Astafyev
 * September, 2019
 * DB "roles" table
 *****/

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "role_seq", sequenceName = "roles_id_seq",
            initialValue = 2, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "role_seq")
    private Long id;

    @Column(name = "role_name")
    private String role_name;

    public Role() {
    }

    public Role(String role_name) {
        this.role_name = role_name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return role_name;
    }

    public void setRoleName(String role_name) {
        this.role_name = role_name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role_name='" + role_name + '\'' +
                '}';
    }
}
