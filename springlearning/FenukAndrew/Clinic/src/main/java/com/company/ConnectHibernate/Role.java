package com.company.ConnectHibernate;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    //Создается только таблица Roles
    @ManyToMany(mappedBy = "roles")
    private List<User3> users;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public Role(String name, List<User3> users) {
        this.name = name;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User3> getUsers() {
        return users;
    }

    public void setUsers(List<User3> users) {
        this.users = users;
    }
}