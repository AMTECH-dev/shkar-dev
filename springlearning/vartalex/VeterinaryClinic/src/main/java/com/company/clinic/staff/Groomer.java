package com.company.clinic.staff;

import com.company.clients.pets.Pet;
import com.company.clinic.cure_methods.Healing;
import com.company.clinic.cure_methods.MedicalService;
import com.company.illnesses.Diagnose;

public class Groomer implements MedicalService {

    @Override
    public void takeCare(Pet pet) {
        Diagnose illness = pet.getDiagnose();

        switch (illness) {
            case NEED_VACCINATION:
            case GASTROINTESTINAL_DISEASES:
            case VIRUS:
            case FRACTURE:
            case TOOTHACHE:
                System.out.println("Groomer can't do that!");
                break;

            default:
                Healing.groom(pet);
                break;
        }
    }

    @Override
    public String toString() {
        return "Groomer";
    }
}
