package clinic.cure_methods;

import clients.pets.Pet;

public abstract class Surgery implements MedicalService {
    public static void giveNarcosis(Pet pet) {
        System.out.println(pet + " undergoes general anesthesia.");
    }
    public static void operate(Pet pet) {
        System.out.println(pet + " is on operation.");
    }
}
