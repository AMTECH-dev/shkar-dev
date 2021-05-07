package fox.tests;

import fox.SEX;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Test {
    public static void main(String[] args) {
        SessionFactory fuck = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(TestPet.class)
                .buildSessionFactory();

        TestPet tmp = null;

        try (Session seshka = fuck.openSession()) {
            Transaction trans = seshka.beginTransaction();
            tmp = seshka.get(TestPet.class, 3);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(tmp);
        
        tmp = new TestPet("Rita", 3, SEX.FEMA, "Brown", "fr-fr", 3);

        try (Session seshka = fuck.openSession()) {
            Transaction trans = seshka.beginTransaction();
            seshka.save(tmp);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(tmp);
    }
}