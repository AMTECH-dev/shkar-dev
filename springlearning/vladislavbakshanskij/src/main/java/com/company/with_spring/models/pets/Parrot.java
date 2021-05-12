package com.company.with_spring.models.pets;

import com.company.with_spring.models.virus.Virus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


public class Parrot implements Pet {
    private String name;
    private final Virus virus;

    public Parrot(Virus virus) {
        System.out.println("Parrot is created");
        this.virus = virus;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public String getName() {
        return name;
    }

    
    public Virus getVirus() {
        return virus;
    }
}
