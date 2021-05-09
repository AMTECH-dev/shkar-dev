package fox.door;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import fox.entities.Pet;
import fox.entities.Doctor;
import fox.entities.Owner;
import fox.entities.PetClinic;


public class Hibernate {
	private static SessionFactory fuck;
	
	static {
		fuck = new Configuration()
               .configure("hibernate.cfg.xml")
               .addAnnotatedClass(PetClinic.class)
               .addAnnotatedClass(Doctor.class)
               .addAnnotatedClass(Pet.class)
               .addAnnotatedClass(Owner.class)
               .buildSessionFactory();
	}
	
	public static PetClinic newClinicRecord(String name, String fias, long phone, Integer webpageIndex, Integer photoDirIndex, String comment) {
		PetClinic clinic = new PetClinic(name, fias, phone, webpageIndex, photoDirIndex, comment);
		
		try (Session seshka = fuck.openSession()) {
			Transaction trans = seshka.beginTransaction();
			seshka.save(clinic);
			trans.commit();
			return clinic;
		} catch (Exception e) {
			System.out.println("Не удалось создать/записать клинику!");
		    e.printStackTrace();
		}
		
		return null;
	}
	
	public static List<?> getClinics() {
		List<?> existsClinics = new ArrayList<>();

		try (Session seshka = fuck.openSession()) {
			seshka.beginTransaction();
			existsClinics = seshka.createQuery("from PetClinic").getResultList();
			seshka.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Не удалось выбрать клиники из базы данных!");
			e.printStackTrace();
			return null;
		}
	
		return existsClinics;
	}
	
	public static boolean dropClinic(PetClinic dClinic) {
		try (Session seshka = fuck.openSession()) {
			Transaction trans = seshka.beginTransaction();
			seshka.delete(dClinic);
			trans.commit();
			return true;
		} catch (Exception e) {
			System.out.println("Не удалось удалить клинику!");
		    e.printStackTrace();
		    return false;
		}
	}
	
	
	public static void close() {fuck.close();}
}