package com.company.without_spring.models.pets;

import com.company.without_spring.models.virus.Virus;

public class Parrot implements Pet {
    private final String name;
    private final Virus virus;

    public Parrot(String name, Virus virus) {
        System.out.println("Parrot is created");
        this.name = name;
        this.virus = virus;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Virus getVirus() {
        return virus;
    }
}
