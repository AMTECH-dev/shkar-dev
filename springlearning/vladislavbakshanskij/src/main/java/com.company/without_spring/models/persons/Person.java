package com.company.without_spring.models.persons;

import com.company.without_spring.models.pets.Pet;

import java.util.List;

public interface Person {
    List<Pet> getPets();
}
