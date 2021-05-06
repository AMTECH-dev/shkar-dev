package com.company.clients;

import com.company.clients.pets.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Owner {
    String name;
    ArrayList<Pet> pets;

    @Autowired
    public Owner(@Value("${owner.defaultName}") String name) {
        this.name = name;
        pets = new ArrayList<>();
        System.out.println("Owner " + name + " appear!");
    }

    public void addPet(Pet ... ownerPets) {
        for (Pet pet : ownerPets) {
            pets.add(pet);
            System.out.printf("Pet %s added to %s.\n", pet, name);
        }
    }

    public ArrayList<Pet> getPets() {
        return pets;
    }

    @Override
    public String toString() {
        return "Owner {" +
                "name = '" + name + '\'' +
                '}';
    }
}
