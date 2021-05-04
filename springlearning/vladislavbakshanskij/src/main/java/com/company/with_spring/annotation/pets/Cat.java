package com.company.with_spring.annotation.pets;

import com.company.with_spring.annotation.virus.Virus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Cat implements Pet {
    @Value("${cat.name}")
    private String name;

    private final Virus virus;

    public Cat(Virus virus) {
        this.virus = virus;
    }

    @Override
    public Virus getVirus() {
        return virus;
    }

    @Override
    public String getName() {
        return name;
    }
}
