package fox.door;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import fox.gui.MonitorFrame;
import fox.spring.SpringEngine;
import fox.tools.IOM;
import fox.tools.IOMs;


public class MainClass {
	
	public static void main(String[] args) {
//		seeOSFonts();		
		System.out.println("Launch the programm!");

		importantDirsCheck();
		buildIOM();

		try {
			new SpringEngine();
			new MonitorFrame();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(114);
		}
	}
	
	private static void importantDirsCheck() {
		File[] impFiles = new File[] {
				new File("./media/"),
				new File("./media/photo")
		};
		
		for (File file : impFiles) {
			if (Files.notExists(file.toPath())) {
				try {Files.createDirectory(file.toPath());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
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