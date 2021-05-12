package com.company.with_spring.models.doctors;

import com.company.with_spring.models.persons.Person;
import com.company.with_spring.models.pets.Pet;
import org.springframework.stereotype.Component;


public class UniversalDoctor implements Doctor {
    
    public boolean healAllPets(Person p) {
        return p.getPets() != null;
    }

    
    public boolean heal(Pet p) {
        return true;
    }

    
    public String getHelp() {
        return "При любой проблеме сразу ко МНЕ в СВОБОДНЫЕ ЧАСЫ!!!!!!";
    }
}
