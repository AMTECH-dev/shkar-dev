package clients.pets.home_pets;

import clients.pets.Pet;
import illnesses.Diagnose;

public class Dog extends Pet {
    public Dog(String name, int ageInMonth, Diagnose diagnose) {
        super(name, ageInMonth, diagnose);
    }
}
