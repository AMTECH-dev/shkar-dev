package fox.door;

import fox.SEX;
import fox.pets.AbstractPet;
import fox.settings.IOM;
import java.awt.*;
import java.io.File;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class MainClass {
	
	public static void main(String[] args) {
//		seeOSFonts();
		test();
		
//		System.out.println("Launch the programm!");
//
//		buildIOM();
//
//		try {
//			new SpringEngine();
//			new MonitorFrame();
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.exit(114);
//		}
	}
	
	public static void test() {
        SessionFactory fuck = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(AbstractPet.class)
                .buildSessionFactory();

        AbstractPet tmp = null;

        try (Session seshka = fuck.openSession()) {
            Transaction trans = seshka.beginTransaction();
            tmp = seshka.get(AbstractPet.class, 3);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(tmp);
        
        tmp = new AbstractPet("Rita", 3, SEX.FEMA, "Brown", "fr-fr", 3);

        try (Session seshka = fuck.openSession()) {
            Transaction trans = seshka.beginTransaction();
            seshka.save(tmp);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(tmp);
    }
	
	private static void seeOSFonts() {
		System.out.println("Fonts:");
		for (Font f : GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts()) {
			System.out.println(f);
		}
		System.out.println();
	}

	private static void buildIOM() {
		IOM.addProperty("GLOBAL", new File("./configurations/global.conf"));
		IOM.set("GLOBAL", "ALLOW_START", "true", false);
	}
}