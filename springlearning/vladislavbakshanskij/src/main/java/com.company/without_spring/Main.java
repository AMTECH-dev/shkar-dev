package com.company.without_spring;

import com.company.without_spring.models.Clinic;
import com.company.without_spring.models.doctors.TraditionalDoctor;
import com.company.without_spring.models.doctors.UniversalDoctor;
import com.company.without_spring.models.persons.CatOwner;
import com.company.without_spring.models.persons.Owner;
import com.company.without_spring.models.persons.Person;
import com.company.without_spring.models.pets.Cat;
import com.company.without_spring.models.pets.Dog;
import com.company.without_spring.models.pets.Parrot;
import com.company.without_spring.models.pets.Pet;
import com.company.without_spring.models.virus.Cold;
import com.company.without_spring.models.virus.Plague;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Pet> cats = Arrays.asList(new Cat("ОЛЕЧКА", null), new Cat("ДАШЕЧКА", new Plague(2)),
                new Cat("ФЕДЕЧКА", new Cold(6)));
        List<Pet> pets = Arrays.asList(new Dog("FRENK", new Plague(6)), new Parrot("ELVIS", new Cold(3)),
                new Parrot("Анток", new Plague(9)));

        Person vlad = new Owner(pets);
        Person oleg = new CatOwner(cats);

        try {
            Person sergey = new CatOwner(pets);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        new Clinic(new TraditionalDoctor(), new UniversalDoctor()).work(vlad, oleg);
    }
}
