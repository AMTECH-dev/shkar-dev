package com.company.with_spring.annotation.owners;

import com.company.with_spring.annotation.pets.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Owner {
    private final List<Pet> pet;

    public Owner(List<Pet> pet) {
        this.pet = pet;
    }

    public List<Pet> getPets() {
        return pet;
    }
}
