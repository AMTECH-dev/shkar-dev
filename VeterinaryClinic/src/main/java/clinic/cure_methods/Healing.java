package clinic.cure_methods;

import clients.pets.Pet;

public abstract class Healing implements MedicalService {
    public static void makeFullCheck(Pet pet) {
        System.out.println(pet + "'s checking complete.");
    }

    public static void takeTemperature(Pet pet) {
        System.out.println(pet + "'s temperature was measured.");
    }

    public static void vaccinate(Pet pet) {
        System.out.println("Doing vaccination of " + pet);
    }

    public static void giveDrug(Pet pet) {
        System.out.println("Drugs are given to " + pet);
    }

    public static void doMassage(Pet pet) {
        System.out.println("Giving massage to " + pet);
    }

    public static void groom(Pet pet) {
        System.out.println(pet + " is grooming.");
    }
}
