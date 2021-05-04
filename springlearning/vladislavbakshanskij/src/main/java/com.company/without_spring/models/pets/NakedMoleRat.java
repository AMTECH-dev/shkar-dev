package com.company.without_spring.models.pets;

import com.company.without_spring.models.virus.Virus;

public class NakedMoleRat implements Pet {
    private final String name;

    public NakedMoleRat(String name) {
        System.out.println("NakedMoleRat is created");
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Virus getVirus() {
        return null;
    }
}
