package clients.pets.farm_animals;

import clients.pets.Pet;
import illnesses.Diagnose;

public class Horse extends Pet {
    public Horse(String name, int ageInMonth, Diagnose diagnose) {
        super(name, ageInMonth, diagnose);
    }
}
