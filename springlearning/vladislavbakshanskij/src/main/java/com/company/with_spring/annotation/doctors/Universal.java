package com.company.with_spring.annotation.doctors;

import com.company.with_spring.annotation.owners.Owner;
import com.company.with_spring.annotation.pets.Pet;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class Universal implements Doctor {
    @Override
    public void healAllPets(Owner owner) {
        System.out.println("I'M HEAL ALL YOUR PETS: " +
                owner.getPets().stream().map(Pet::getName).collect(Collectors.joining(",")));
    }

    @Override
    public void heal(Pet p) {
        System.out.println("HEAL YOUR PET: " + p.getName());
    }
}
