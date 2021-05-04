package clinic.administration;

import clients.Owner;
import clients.pets.Pet;
import clinic.staff.Groomer;
import clinic.cure_methods.MedicalService;
import clinic.staff.Surgeon;
import clinic.staff.Therapist;

import java.util.*;

public class Office {
    private static Map<Owner, ArrayList<Pet>> clients;

    List<MedicalService> doctors;

    public Office() {
        doctors = new ArrayList<>();
        clients = new HashMap<>();
        createStaff();
    }

    private void createStaff() {
        doctors.addAll(Arrays.asList(new Therapist(), new Surgeon(), new Groomer()));
        System.out.println("Персонал создан!");
    }

    public static void addOwnerToTheBase(Owner owner) {
        clients.put(owner, owner.getPets());
    }

    public static Map<Owner, ArrayList<Pet>> getClientBase() {
        return clients;
    }

    public List<MedicalService> getDoctors() {
        return doctors;
    }

    public void getSlogan() {
        System.out.println("ПОМОЖЕМ ВСЕМ ЖИВОТНЫМ. P.S. ЛЮДЯМ МОЖЕТ БЫТЬ ТОЖЕ.");
    }
}
