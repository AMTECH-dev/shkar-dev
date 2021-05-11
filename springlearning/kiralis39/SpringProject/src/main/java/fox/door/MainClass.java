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
		IOM.set(IOMs.GLOBAL.class, IOMs.GLOBAL.USE_RENDER, "true", false);
	}
}