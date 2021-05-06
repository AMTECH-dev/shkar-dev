package com.company;

import com.company.clients.Owner;
import com.company.clients.pets.Pet;
import com.company.clients.pets.home_pets.*;
import com.company.clinic.Office;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        System.out.println("Program launched!");

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("app.xml");

        Office clinic = context.getBean(Office.class);

        Pet cat = context.getBean(Cat.class);
        Pet dog = context.getBean(Dog.class);

        Owner owner = context.getBean(Owner.class);
        owner.addPet(cat, dog);

        clinic.addOwnersToTheBase(owner);

        clinic.takeCare(cat);

        cat.isSick();
        dog.isSick();
        clinic.takeCare(dog);
        dog.isSick();
        clinic.takeCare(dog);

        context.close();

        // без ioc, di
/*
        Office clinic = new Office();

        Pet kitty = new Cat("John", 3, Diagnose.NEED_VACCINATION);
        Pet doggy = new Dog("Fluffy", 24, Diagnose.VIRUS);
        Owner owner1 = new Owner("William Shakespeare");
        owner1.addPet(kitty);
        owner1.addPet(doggy);

        System.out.println(owner1 + "'s pets:");
        System.out.println("\n" + owner1.getPets());

        Pet snaky = new Snake("Bold Head", 12, Diagnose.FRACTURE);
        Owner owner2 = new Owner("Kate Smith");
        owner2.addPet(snaky);
        clinic.addOwnerToTheBase(owner1, owner2);

        System.out.println("Clinic clients: ");
        System.out.println("\n" + clinic.getClientBase() + "\n");

        clinic.takeCare(kitty);

        doggy.isSick();
        clinic.takeCare(doggy);
        doggy.isSick();
        clinic.takeCare(doggy);

        clinic.takeCare(snaky);
 */
    }
}
