/*
общая суть:
- клиника, у которой есть ветеринары.
- есть врачи, которые занимаются лечением животных, при чем у каждого есть особые способы лечения.
- животные может иметь несколько заболеваний.
- у каждого животного есть хозяева.

1) описываем структуру классов, их связи
2) в каждом классе в конструкторе вызываем sout, чтобы видеть, кто когда инициализировался
 */

import clients.Owner;
import clients.pets.Pet;
import clients.pets.home_pets.*;
import clinic.administration.Office;
import illnesses.Diagnose;

public class Main {
    public static void main(String[] args) {
        Office clinic = new Office();

        Pet kitty = new Cat("John", 3, Diagnose.NEED_VACCINATION);
        Pet doggy = new Dog("Fluffy", 24, Diagnose.VIRUS);
        Owner owner1 = new Owner("William Shakespeare", "12.04.1587");
        owner1.addPet(kitty);
        owner1.addPet(doggy);
        clinic.addOwnerToTheBase(owner1);

        System.out.println(owner1 + "'s pets:");
        System.out.println("\n" + owner1.getPets());

        Pet snaky = new Snake("Bold Head", 12, Diagnose.FRACTURE);
        Owner owner2 = new Owner("Kate Smith", "12.32.1976");
        owner2.addPet(snaky);
        clinic.addOwnerToTheBase(owner2);

        System.out.println("Clinic clients: ");
        System.out.println("\n" + clinic.getClientBase() + "\n");

        clinic.getTherapist().takeCare(kitty);

        doggy.isSick();
        clinic.getSurgeon().takeCare(doggy);
        doggy.isSick();
        clinic.getGroomer().takeCare(doggy);

        clinic.getGroomer().takeCare(snaky);
    }
}
