package com.company.with_spring.annotation.doctors;

import com.company.with_spring.annotation.owners.Owner;
import com.company.with_spring.annotation.pets.Pet;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.stream.Collectors;

@Component
public class Traditional implements Doctor {
    @Override
    public void healAllPets(Owner owner) {
        var isGood = new Random().nextInt() % 2 == 0;
        var petNames = owner.getPets().stream().map(Pet::getName).collect(Collectors.joining(", "));
        System.out.println(isGood ? "I'M HEAL ALL YOUR PETS: " + petNames : "FUCK: " + petNames);
    }

    @Override
    public void heal(Pet p) {
        var isGood = new Random().nextInt() % 2 == 0;
        System.out.println(isGood ? "I'M HEAL YOUR PET: " + p.getName() : "FUCK: " + p.getName());
    }
}
