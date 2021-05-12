package com.company.Clinic;

import com.company.Clinic.Animals.Pet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Master {
    private String name;
    private List<Pet> pets;

    public Master(String name,List<Pet> pets) {
        this.name=name;
        this.pets = pets;
        System.out.println("Master is created");
    }

    public Master() {
        this("",new ArrayList<Pet>());
    }

    public Master(String name,Pet pet) {
        this(name, Arrays.asList(pet));
    }

    public void setName(String name) {
        this.name=name;
        System.out.println("Master set name:"+name);
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append(Master.class.getName()+":"+name+"\n");
        for(Pet pet : pets) {
            sb.append(pet.toString());sb.append("\n");
        }
        return sb.toString();
    }

    public String getName() {
        return name;
    }
}
