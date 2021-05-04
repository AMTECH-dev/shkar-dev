package com.company.without_spring.models.pets;

import com.company.without_spring.models.virus.Virus;

public class Dog implements Pet {
    private final String name;
    private final Virus virus;

    public Dog(String name, Virus virus) {
        System.out.println("Dog is created");
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
