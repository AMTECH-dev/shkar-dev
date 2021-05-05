package com.company;

import com.company.clients.Owner;
import com.company.clients.pets.Pet;
import com.company.clients.pets.home_pets.*;
import com.company.clinic.administration.Office;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        System.out.println("Program launched!");

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("app.xml");

        Office clinic = context.getBean(Office.class);

        Pet cat = context.getBean(Cat.class);
        Pet dog = context.getBean(Dog.class);

        Owner owner = context.getBean(Owner.class);
        owner.addPet(cat);
        owner.addPet(dog);

        clinic.addOwnerToTheBase(owner);

        clinic.getTherapist().takeCare(cat);

        cat.isSick();
        dog.isSick();
        clinic.getSurgeon().takeCare(dog);
        dog.isSick();
        clinic.getTherapist().takeCare(dog);
        clinic.getGroomer().takeCare(dog);

        context.close();

//        Office com.company.clinic = new Office();
//
//        Pet kitty = new Cat("John", 3, Diagnose.NEED_VACCINATION);
//        Pet doggy = new Dog("Fluffy", 24, Diagnose.VIRUS);
//        Owner owner1 = new Owner("William Shakespeare", "12.04.1587");
//        owner1.addPet(kitty);
//        owner1.addPet(doggy);
//        com.company.clinic.addOwnerToTheBase(owner1);
//
//        System.out.println(owner1 + "'s pets:");
//        System.out.println("\n" + owner1.getPets());
//
//        Pet snaky = new Snake("Bold Head", 12, Diagnose.FRACTURE);
//        Owner owner2 = new Owner("Kate Smith", "12.32.1976");
//        owner2.addPet(snaky);
//        com.company.clinic.addOwnerToTheBase(owner2);
//
//        System.out.println("Clinic com.company.clients: ");
//        System.out.println("\n" + com.company.clinic.getClientBase() + "\n");
//
//        com.company.clinic.getTherapist().takeCare(kitty);
//
//        doggy.isSick();
//        com.company.clinic.getSurgeon().takeCare(doggy);
//        doggy.isSick();
//        com.company.clinic.getGroomer().takeCare(doggy);
//
//        com.company.clinic.getGroomer().takeCare(snaky);
    }
}
