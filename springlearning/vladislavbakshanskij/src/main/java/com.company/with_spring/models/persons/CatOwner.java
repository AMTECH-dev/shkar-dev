package com.company.with_spring.models.persons;

import com.company.with_spring.models.pets.Cat;
import com.company.with_spring.models.pets.Pet;

import java.util.List;

public class CatOwner implements Person {
    private final List<Pet> cats;

    public CatOwner(List<Pet> pets) {
        boolean isInit = true;
        for (Pet pet : pets) {
            if (!(pet instanceof Cat)) {
                System.err.println("Я ЛЮБЛЮ ТОЛЬКО КОШЕК");
                isInit = false;
                break;
            }
        }

        System.out.println("Cat owner is created");
        this.cats = isInit ? pets : null;
    }

    @Override
    public List<Pet> getPets() {
        return cats;
    }
}
