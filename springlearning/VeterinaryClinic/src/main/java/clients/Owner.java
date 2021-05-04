package clients;

import clients.pets.Pet;

import java.util.ArrayList;

public class Owner {
    String name;
    String dateOfBirth;
    ArrayList<Pet> pets;

    public Owner(String name, String dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        pets = new ArrayList<>();
    }

    public void addPet(Pet pet) {
        pets.add(pet);
        System.out.printf("Pet %s added to %s.\n", pet, name);
    }

    public ArrayList<Pet> getPets() {
        return pets;
    }

    @Override
    public String toString() {
        return "Owner {" +
                "name = '" + name + '\'' +
                ", dateOfBirth = '" + dateOfBirth + '\'' +
                '}';
    }
}
