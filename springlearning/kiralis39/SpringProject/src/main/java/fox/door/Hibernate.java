package fox.door;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import fox.entities.Doctor;
import fox.entities.Owner;
import fox.entities.Pet;
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
	
	public static PetClinic writeClinic(PetClinic cl) {
		try (Session seshka = fuck.openSession()) {
			seshka.beginTransaction();
			seshka.saveOrUpdate(cl);			
			seshka.getTransaction().commit();
			return cl;
		} catch (Exception e) {
			System.out.println("Не удалось создать/записать клинику!");
		    e.printStackTrace();
		}
		
		return null;
	}
	
	public static Doctor writeWildDoctor(Doctor d) {
		try (Session session = fuck.openSession()) {
			session.beginTransaction();
			session.saveOrUpdate(d);
			session.getTransaction().commit();
			return d;
		} catch (Exception e) {
			System.out.println("Не удалось создать/записать доктора!");
		    e.printStackTrace();
		}
		
		return null;
	}
	
	
	public static List<PetClinic> getClinics() {
		List<PetClinic> existsClinics = null;

		try (Session seshka = fuck.openSession()) {
			seshka.beginTransaction();
			existsClinics = seshka.createQuery("from PetClinic", PetClinic.class).getResultList();
			seshka.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Не удалось выбрать клиники из базы данных!");
			e.printStackTrace();
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
	
	public static void close() {
		if (fuck != null && fuck.isOpen()) {
			fuck.close();
		}
	}

	
	public static void writeDoctor(PetClinic clinic, Doctor aNewDoc) {
		try (Session seshka = fuck.openSession()) {
			seshka.beginTransaction();
			seshka.saveOrUpdate(clinic);
			seshka.save(aNewDoc);
			seshka.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Не удалось выбрать клиники из базы данных!");
			e.printStackTrace();
		}
	}
}