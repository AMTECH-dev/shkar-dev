package com.company.with_spring.models.pets;

import com.company.with_spring.models.virus.Virus;
import org.springframework.stereotype.Component;


public class NakedMoleRat implements Pet {
    private String name;

    public NakedMoleRat() {
        System.out.println("NakedMoleRat is created");
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public String getName() {
        return name;
    }

    
    public Virus getVirus() {
        return null;
    }
}
