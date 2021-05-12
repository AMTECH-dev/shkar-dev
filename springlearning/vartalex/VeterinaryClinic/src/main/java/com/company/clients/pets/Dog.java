package com.company.clients.pets;

import com.company.illnesses.Diagnose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Dog extends Pet {
    @Autowired
    public Dog(@Value("${dog.defaultName}") String name,
               @Value("${dog.defaultAgeInMonth}") int ageInMonth,
               @Value("${dog.defaultDiagnose}") Diagnose diagnose) {
        super(name, ageInMonth, diagnose);
    }
}
