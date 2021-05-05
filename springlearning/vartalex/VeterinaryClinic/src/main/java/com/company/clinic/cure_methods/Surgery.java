package com.company.clinic.cure_methods;

import com.company.clients.pets.Pet;

public abstract class Surgery implements MedicalService {
    public static void giveNarcosis(Pet pet) {
        System.out.println(pet + " undergoes general anesthesia.");
    }
    public static void operate(Pet pet) {
        System.out.println(pet + " is on operation.");
    }
}
