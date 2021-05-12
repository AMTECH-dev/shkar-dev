package com.company.ConnectHibernate;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users2")
public class User2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @OneToMany
    //Name of new join column
    @JoinColumn(name="ss")
    private List<Contact2> contacts;

    public User2(String username, List<Contact2> contacts) {
        this.username = username;
        this.contacts = contacts;
    }

    public User2() {
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

    public List<Contact2> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact2> contacts) {
        this.contacts = contacts;
    }
}