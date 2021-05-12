import clients.*;
import clients.pets.*;
import clinic.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(VetClinic.class)
                .addAnnotatedClass(Doctor.class)
                .addAnnotatedClass(Owner.class)
                .addAnnotatedClass(Pet.class)
                .buildSessionFactory();
        Session session = null;

        try {
            session = factory.openSession();
            session.beginTransaction();

            manipulateDBData(session);

            session.getTransaction().commit();
        }
        finally {
            factory.close();
            if (session != null) session.close();
        }
    }

    private static void manipulateDBData(Session session) {
//        VetClinic clinic = new VetClinic("OopsClinic");
//        VetClinic clinic2 = new VetClinic("Non, je ne regrette rien");

//        Balin, Dwalin, Dori, Nori, Ori, Oin, Gloin, Bifur, Bofur, Bombur, Fili, and Kili
//        Haldir, Arwen, Thranduil, Celebrimbor, Legolas, Celeborn, Glorfindel

//        Owner owner1 = new Owner("Haldir");
//        Owner owner2 = new Owner("Arwen");
//        Owner owner3 = new Owner("Thranduil");
//        Owner owner4 = new Owner("Legolas");
//
//        Pet cat = new Cat("Balin", 3, owner1, Illness.NONE.getNameOfIllness());
//        Pet dog = new Dog("Dwalin", 24, owner1, Illness.TOOTHACHE.getNameOfIllness());
//
//        Pet snail = new Snail("Dori", 2, owner2, Illness.FRACTURE.getNameOfIllness());
//
//        Pet pet = new Cat("Nori", 3, owner3, Illness.VIRUS.getNameOfIllness());
//        Pet pet1 = new Dog("Ori", 12, owner4, Illness.GASTRITIS.getNameOfIllness());
//        Pet pet2 = new Cat("Oin", 1, owner4, Illness.NEED_VACCINATION.getNameOfIllness());
//        Pet pet3 = new Snail("Gloin", 1, owner4, Illness.NONE.getNameOfIllness());
//
//        owner1.setPets(Arrays.asList(cat, dog));
//        owner2.setPets(Collections.singletonList(snail));
//        owner3.setPets(Collections.singletonList(pet));
//        owner4.setPets(Arrays.asList(pet1, pet2, pet3));
//
//        owner1.addClinic(clinic);
//        owner2.addClinic(clinic);
//        owner3.addClinic(clinic);
//        owner1.addClinic(clinic2);
//        owner4.addClinic(clinic2);
//
//        Doctor doctor = new Doctor("Evil", clinic);
//        Doctor doctor1 = new Doctor("Good", clinic);
//        Doctor doctor2 = new Doctor("Who", clinic);
//        Doctor doctor3 = new Doctor("Esculap", clinic2);
//        Doctor doctor4 = new Doctor("Hippo", clinic2);
//
//        clinic.setDoctors(Arrays.asList(doctor, doctor1, doctor2));
//        clinic.addClients(Arrays.asList(owner1, owner2, owner3));
//        clinic2.setDoctors(Arrays.asList(doctor3, doctor4));
//        clinic2.addClients(Arrays.asList(owner1, owner4));
//
//        session.save(clinic);
//        session.save(clinic2);
        System.out.println(session.get(VetClinic.class, 21));
    }
}
