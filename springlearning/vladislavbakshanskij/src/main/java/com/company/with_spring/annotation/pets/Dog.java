package com.company.with_spring.annotation.pets;

import com.company.with_spring.annotation.ColdQualifier;
import com.company.with_spring.annotation.virus.Virus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Dog implements Pet {
    @Value("${dog.name}")
    private String name;

    private final Virus virus;

    public Dog(@ColdQualifier Virus virus) {
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
