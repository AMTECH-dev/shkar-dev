package com.company.without_spring.models.persons;

import com.company.without_spring.models.pets.Cat;
import com.company.without_spring.models.pets.Pet;

import java.util.List;

public class CatOwner implements Person {
    private final List<Pet> cats;

    public CatOwner(List<Pet> cats) {
        for (Pet cat : cats) {
            if (!(cat instanceof Cat)) {
                throw new RuntimeException("Я ЛЮБЛЮ ТОЛЬКО КОШЕК!!!!!");
            }
        }

        System.out.println("Cat owner is created");
        this.cats = cats;
    }

    @Override
    public List<Pet> getPets() {
        return cats;
    }
}
