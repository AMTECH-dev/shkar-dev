package com.company.clients;

import com.company.clients.pets.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Owner {
    String name;
    String dateOfBirth;
    ArrayList<Pet> pets;

    @Autowired
    public Owner(@Value("${owner.defaultName}") String name,
                 @Value("${owner.defaultDateOfBirth}") String dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        pets = new ArrayList<>();
        System.out.println("Owner " + name + " appear!");
    }

    public void addPet(Pet pet) {
        pets.add(pet);
        System.out.printf("Pet %s added to %s.\n", pet, name);
    }

    public ArrayList<Pet> getPets() {
        return pets;
    }

    @Override
    public String toString() {
        return "Owner {" +
                "name = '" + name + '\'' +
                ", dateOfBirth = '" + dateOfBirth + '\'' +
                '}';
    }
}
