package clients.pets.home_pets;

import clients.pets.Pet;
import illnesses.Diagnose;

public class Cat extends Pet {
    public Cat(String name, int ageInMonth, Diagnose diagnose) {
        super(name, ageInMonth, diagnose);
    }
}
