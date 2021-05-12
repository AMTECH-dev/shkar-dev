package com.company.with_spring.models.persons;

import com.company.with_spring.models.pets.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


public class Owner implements Person {
    private List<Pet> pets;

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public List<Pet> getPets() {
        return pets;
    }
}
