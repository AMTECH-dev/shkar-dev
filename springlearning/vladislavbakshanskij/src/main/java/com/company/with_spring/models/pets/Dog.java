package com.company.with_spring.models.pets;

import com.company.with_spring.models.virus.Virus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Dog implements Pet {
    private  String name;
    private final Virus virus;

    public Dog(@Qualifier("plague") Virus virus) {
        System.out.println("Dog is created");
        this.virus = virus;
    }

    public void setName(String name) {
        this.name = name;
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
