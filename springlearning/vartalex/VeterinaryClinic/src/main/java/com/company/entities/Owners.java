package com.company.entities;

import com.company.clients.Owner;

import javax.persistence.*;

@Entity
@Table(name = "owners")
public class Owners {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_id")
    private int id;

    @Column(name = "name")
    private String name;

    public Owners() {
    }

    public Owners(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
