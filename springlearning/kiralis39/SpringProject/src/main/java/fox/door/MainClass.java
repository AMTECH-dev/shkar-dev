package fox.door;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;

import fox.gui.MonitorFrame;
import fox.spring.SpringEngine;
import fox.tools.IOM;
import fox.tools.IOMs;


public class MainClass {
	
	public static void main(String[] args) {
//		seeOSFonts();		
		System.out.println("Launch the programm!");

		buildIOM();

		try {
			new SpringEngine();
			new MonitorFrame();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(114);
		}
	}
	
	void test() {
//        AbstractPet tmp = null;
//        try (Session seshka = fuck.openSession()) {
//            Transaction trans = seshka.beginTransaction();
//            tmp = seshka.get(AbstractPet.class, 3);
//            trans.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        tmp = new AbstractPet("Rita", 3, SEX.FEMA, "Brown", "fr-fr", null);
//
//        Doctor tmp = new Doctor("dr.Watson", 42, "Moscow, Krasnaya, 1", 26495, SEX.FEMA);
    }
	
	void seeOSFonts() {
		System.out.println("Fonts:");
		for (Font f : GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts()) {
			System.out.println(f);
		}
		System.out.println();
	}

	private static void buildIOM() {
		IOM.addProperty(IOMs.GLOBAL.class, new File("./configurations/global.conf"));
		IOM.set(IOMs.GLOBAL.class, IOMs.GLOBAL.ALLOW_START, "true", false);
		IOM.set(IOMs.GLOBAL.class, IOMs.GLOBAL.PROGRAMM_NAME, "Funny pets clinic manager", false);
		IOM.set(IOMs.GLOBAL.class, IOMs.GLOBAL.PROGRAMM_VERSE, "1.0.0", false);
	}
}