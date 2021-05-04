package com.company.with_spring.models.persons;

import com.company.with_spring.models.pets.Pet;

import java.util.List;

public interface Person {
    List<Pet> getPets();
}
