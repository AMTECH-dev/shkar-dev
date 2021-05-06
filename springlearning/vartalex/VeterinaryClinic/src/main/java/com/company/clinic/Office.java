package com.company.clinic;

import com.company.clients.Owner;
import com.company.clients.pets.Pet;
import com.company.illnesses.Diagnose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Office {
    private final Map<Owner, ArrayList<Pet>> clients;
    private final ArrayList<Doctor> doctorsList;

    @Autowired
    public Office() {
        System.out.println("Clinic created!");
        clients = new HashMap<>();
        doctorsList = new ArrayList<>();
    }

    private Doctor callDoctor() {
        return new Doctor();
    }

    public void takeCare(Pet pet) {
        Doctor doctor = this.callDoctor();
        doctorsList.add(doctor);

        switch (pet.getDiagnose()) {
            case NEED_VACCINATION:
                doctor.vaccinate(pet);
                break;
            case GASTROINTESTINAL_DISEASES:
            case TOOTHACHE:
                doctor.operate(pet);
                break;
            case VIRUS:
                doctor.takeTemperature(pet);
                doctor.giveDrug(pet);
                break;
            case FRACTURE:
                doctor.operate(pet);
                doctor.doMassage(pet);
                break;
            default:
                doctor.makeFullCheck(pet);
                break;
        }
        pet.setDiagnose(Diagnose.NONE);
    }

    public void addOwnersToTheBase(Owner... owners) {
        for (Owner owner : owners) {
            clients.put(owner, owner.getPets());
            System.out.println(owner + " added to the base.");
        }
    }

    public Map<Owner, ArrayList<Pet>> getClientBase() {
        return clients;
    }

    public ArrayList<Doctor> getDoctorsList() {
        return doctorsList;
    }

    @Override
    public String toString() {
        return "Office{" +
                "clients=" + clients +
                ", doctorsList=" + doctorsList +
                '}';
    }
}
