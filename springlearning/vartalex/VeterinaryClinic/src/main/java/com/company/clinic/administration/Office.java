package com.company.clinic.administration;

import com.company.clients.Owner;
import com.company.clients.pets.Pet;
import com.company.clinic.staff.*;
import com.company.clinic.cure_methods.MedicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Office {
    private final Map<Owner, ArrayList<Pet>> clients;
    private Therapist therapist;
    private Surgeon surgeon;
    private Groomer groomer;

    @Autowired
    public Office() {
        System.out.println("Clinic created!");
        clients = new HashMap<>();
        createStaff();
    }

    private void createStaff() {
        therapist = new Therapist();
        surgeon = new Surgeon();
        groomer = new Groomer();
        System.out.println("Персонал создан!");
    }

    public void addOwnerToTheBase(Owner owner) {
        clients.put(owner, owner.getPets());
    }

    public Map<Owner, ArrayList<Pet>> getClientBase() {
        return clients;
    }

    public MedicalService getTherapist() {
        return therapist;
    }

    public MedicalService getSurgeon() {
        return surgeon;
    }

    public MedicalService getGroomer() {
        return groomer;
    }

    @Override
    public String toString() {
        return "Office{" +
                "com.company.clients=" + clients +
                '}';
    }
}
