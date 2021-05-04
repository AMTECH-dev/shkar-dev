package clinic.staff;

import clients.pets.Pet;
import clinic.cure_methods.Healing;
import clinic.cure_methods.MedicalService;
import illnesses.Diagnose;

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

            case NONE:
                Healing.groom(pet);
                break;
        }
        Healing.groom(pet);
    }

    @Override
    public String toString() {
        return "Groomer";
    }
}
