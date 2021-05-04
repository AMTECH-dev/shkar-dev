package clinic.staff;

import clients.pets.Pet;
import clinic.cure_methods.Healing;
import clinic.cure_methods.MedicalService;
import clinic.cure_methods.Surgery;
import illnesses.Diagnose;

public class Surgeon implements MedicalService {
    @Override
    public void takeCare(Pet pet) {
        Diagnose illness = pet.getDiagnose();

        switch (illness) {
            case NEED_VACCINATION:
                Healing.vaccinate(pet);
                break;
            case GASTROINTESTINAL_DISEASES:
            case FRACTURE:
            case TOOTHACHE:
                doOperation(pet);
                break;
            case VIRUS:
                Healing.takeTemperature(pet);
                Healing.giveDrug(pet);
                break;
            case NONE:
                Healing.makeFullCheck(pet);
                break;
        }

        pet.setDiagnose(Diagnose.NONE);
    }

    private static void doOperation(Pet pet) {
        Surgery.giveNarcosis(pet);
        Surgery.operate(pet);
    }

    @Override
    public String toString() {
        return "Surgeon";
    }
}
