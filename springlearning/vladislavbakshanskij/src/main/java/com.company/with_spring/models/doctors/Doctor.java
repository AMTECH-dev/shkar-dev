package com.company.with_spring.models.doctors;

import com.company.with_spring.models.persons.Person;
import com.company.with_spring.models.pets.Pet;

public interface Doctor {
    boolean healAllPets(Person p);
    boolean heal(Pet p);
    String getHelp();
}
