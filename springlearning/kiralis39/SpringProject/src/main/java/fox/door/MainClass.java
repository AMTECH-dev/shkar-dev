package fox.door;

import fox.gui.MonitorFrame;
import fox.settings.IOM;

import java.awt.*;
import java.io.File;
import java.util.Arrays;


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