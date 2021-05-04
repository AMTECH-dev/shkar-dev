package clinic.staff;

import clients.pets.Pet;
import clinic.cure_methods.*;
import illnesses.Diagnose;

public class Therapist extends Healing {

    @Override
    public void takeCare(Pet pet) {
        Diagnose illness = pet.getDiagnose();

        switch (illness) {
            case NEED_VACCINATION:
                Healing.vaccinate(pet);
                break;
            case GASTROINTESTINAL_DISEASES:
                Healing.giveDrug(pet);
                break;
            case VIRUS:
                Healing.takeTemperature(pet);
                Healing.giveDrug(pet);
                break;
            case FRACTURE:
                Healing.doMassage(pet);
                break;
            case TOOTHACHE:
                System.out.println("Therapist can't cure toothache!");
                break;
            case NONE:
                Healing.makeFullCheck(pet);
                break;
        }

        pet.setDiagnose(Diagnose.NONE);
    }

    @Override
    public String toString() {
        return "Therapist";
    }
}
