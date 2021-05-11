package com.company.clients.pets;

import com.company.illnesses.Diagnose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Cat extends Pet {
    @Autowired
    public Cat(@Value("${pet.defaultName}") String name,
               @Value("${pet.defaultAgeInMonth}") int ageInMonth,
               @Value("${pet.defaultDiagnose}") Diagnose diagnose) {
        super(name, ageInMonth, diagnose);
    }
}
