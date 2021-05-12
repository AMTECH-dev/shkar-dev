package com.company.ConnectHibernate;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users3")
public class User3 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @ManyToMany
    private List<Role> roles;

    public User3() {
    }

    public User3(String username) {
        this.username = username;
    }

    public User3(String username, List<Role> roles) {
        this.username = username;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}