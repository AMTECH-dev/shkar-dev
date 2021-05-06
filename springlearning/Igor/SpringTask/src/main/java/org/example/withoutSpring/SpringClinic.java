package org.example.withoutSpring;

import org.example.withoutSpring.animals.Dog;
import org.example.withoutSpring.doctors.Lor;
import org.example.withoutSpring.doctors.Surgeon;
import org.example.withoutSpring.owner.Owner;
import org.example.withoutSpring.util.Clinic;


public class SpringClinic {


    public static void main(String[] args) {

        Dog dog = new Dog("bobik", 5, "dog");
        dog.say();
        Owner owner = new Owner("Serg", dog.getAnimalType());
        owner.addOwner(dog.getAnimalType());
        Surgeon surgeon = new Surgeon("Andrew", "accountant", 18);
        Lor lor = new Lor("Ig", "LOR", 38);
        lor.virus();
        Clinic clinic = new Clinic(lor);
        Clinic clinic2 = new Clinic(surgeon);
        clinic.addToList();

    }
}
