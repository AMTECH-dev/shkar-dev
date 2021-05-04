package com.company.without_spring.models.doctors;

import com.company.without_spring.models.persons.Person;
import com.company.without_spring.models.pets.Pet;

public class UniversalDoctor implements Doctor {
    @Override
    public boolean healAllPets(Person p) {
        return p.getPets() != null;
    }

    @Override
    public boolean heal(Pet p) {
        return true;
    }

    @Override
    public String getHelp() {
        return "При любой проблеме сразу ко МНЕ в СВОБОДНЫЕ ЧАСЫ!!!!!!";
    }
}
