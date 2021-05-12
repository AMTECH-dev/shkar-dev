package org.example.withoutSpring.owner;

import java.util.ArrayList;
import java.util.List;

public class Owner {
    private String name;
    private String animalType;

    public Owner(String name, String animal) {
        this.name = name;
        this.animalType = animal;
    }

    public String getName() {
        return name;
    }

    public void addOwner(String animalType) {
        List<String> ownerList = new ArrayList<>();
        ownerList.add(animalType);
        System.out.println("У животного появился хозяин по имени " + getName());
    }
}
