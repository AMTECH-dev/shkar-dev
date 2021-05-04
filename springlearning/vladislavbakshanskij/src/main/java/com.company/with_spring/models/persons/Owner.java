package com.company.with_spring.models.persons;

import com.company.with_spring.models.pets.Pet;

import java.util.List;

public class Owner implements Person {
    private List<Pet> pets;

    public Owner(List<Pet> pets) {
        System.out.println("Owner is created");
        this.pets = pets;
    }

    @Override
    public List<Pet> getPets() {
        return pets;
    }
}
