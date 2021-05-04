package com.company.with_spring.annotation.doctors;

import com.company.with_spring.annotation.owners.Owner;
import com.company.with_spring.annotation.pets.Pet;

public interface Doctor {
    void healAllPets(Owner owner);
    void heal(Pet p);
}
