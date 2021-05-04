package com.company.with_spring.models.persons;

import com.company.with_spring.models.pets.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Owner implements Person {
    @Autowired
    private List<Pet> pets;

    @Override
    public List<Pet> getPets() {
        return pets;
    }
}
