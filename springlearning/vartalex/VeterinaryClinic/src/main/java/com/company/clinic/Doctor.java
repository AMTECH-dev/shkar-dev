package com.company.clinic;

import com.company.clients.pets.Pet;

public class Doctor {

    public Doctor() {
        System.out.println(Doctor.class.getSimpleName() + " came to patient.");
    }

    public void makeFullCheck(Pet pet) {
        System.out.println(pet + "'s checking complete.");
    }

    public void takeTemperature(Pet pet) {
        System.out.println(pet + "'s temperature was measured.");
    }

    public void vaccinate(Pet pet) {
        System.out.println("Doing vaccination of " + pet);
    }

    public void giveDrug(Pet pet) {
        System.out.println("Drugs are given to " + pet);
    }

    public void doMassage(Pet pet) {
        System.out.println("Giving massage to " + pet);
    }

    public void operate(Pet pet) {
        System.out.println(pet + " is on operation.");
    }

    @Override
    public String toString() {
        return "Doctor{}";
    }
}
