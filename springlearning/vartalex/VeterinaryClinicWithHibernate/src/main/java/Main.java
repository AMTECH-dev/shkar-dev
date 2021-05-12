import clients.*;
import clients.pets.*;
import clinic.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
/*
            Owner owner = new Owner("Kate");
            Owner owner2 = new Owner("Bill");

            Pet cat = new Cat("Tigra", 3, owner, Illness.NONE.getNameOfIllness());
            Pet dog = new Dog("Gogo", 24, owner, Illness.TOOTHACHE.getNameOfIllness());

            Pet snail = new Snail("Lol", 2, owner2, Illness.FRACTURE.getNameOfIllness());

            owner.setPets(Arrays.asList(cat, dog));
            owner2.setPets(Arrays.asList(snail));

//            VetClinic clinic = new VetClinic("OopsClinic");
            VetClinic clinic = session.get(VetClinic.class, 1);
            owner.setClinic(clinic);
            owner2.setClinic(clinic);

            Doctor doctor = session.get(Doctor.class, 1);
            Doctor doctor2 = session.get(Doctor.class, 2);

            clinic.setDoctors(Arrays.asList(doctor, doctor2));
            clinic.setClients(Arrays.asList(owner, owner2));

            session.save(clinic);
*/
        System.out.println(session.get(VetClinic.class, 1));
    }
}
