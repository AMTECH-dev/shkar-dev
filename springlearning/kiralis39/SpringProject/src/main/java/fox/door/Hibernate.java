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
import fox.entities.clinicData.Photodir;
import fox.entities.clinicData.Webpage;
import fox.gui.MonitorFrame;


public class Hibernate {
	private static SessionFactory fuck;
	
	static {
		try {
			fuck = new Configuration()
               .configure("hibernate.cfg.xml")
               // ---
               .addAnnotatedClass(PetClinic.class)
               .addAnnotatedClass(Webpage.class)
               .addAnnotatedClass(Photodir.class)
               // ---
               .addAnnotatedClass(Doctor.class)
               // ---
               .addAnnotatedClass(Pet.class)
               .addAnnotatedClass(Owner.class)
               // ---
               .buildSessionFactory();
		} catch (Exception e) {
			System.out.println();
			e.printStackTrace();
			MonitorFrame.endWorkEndExit(44);
		}		
	}
	
	public static PetClinic newClinicRecord(String name, String fias, long phone, Webpage webpage, Photodir photodir, String comment) {
		PetClinic clinic = new PetClinic(name, fias, phone, webpage, photodir, comment);
		
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
	
	public static PetClinic newClinicRecord(PetClinic clCreated) {
		try (Session seshka = fuck.openSession()) {
			Transaction trans = seshka.beginTransaction();
			seshka.save(clCreated);
			trans.commit();
			return clCreated;
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
	
//	public static String getWebPageOf(PetClinic clinic) {
//		String url = "NA";
//		
//		try (Session seshka = fuck.openSession()) {
//			seshka.beginTransaction();
//			url = (String) seshka.createQuery("from webpages where webpages.id="+clinic.getUrlIndex()).getSingleResult();
//			seshka.getTransaction().commit();
//			return url;
//		} catch (Exception e) {
//			System.out.println("Не удалось удалить клинику!");
//		    e.printStackTrace();
//		}
//		
//		return url;
//	}
	
	public static void close() {
		if (fuck != null && fuck.isOpen()) {
			fuck.close();
		}
	}
}