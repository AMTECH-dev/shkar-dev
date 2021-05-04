package clinic.administration;

import clients.Owner;
import clients.pets.Pet;
import clinic.staff.*;
import clinic.cure_methods.MedicalService;

import java.util.*;

public class Office {
    private final Map<Owner, ArrayList<Pet>> clients;
    private Therapist therapist;
    private Surgeon surgeon;
    private Groomer groomer;

    public Office() {
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
                "clients=" + clients +
                '}';
    }
}
