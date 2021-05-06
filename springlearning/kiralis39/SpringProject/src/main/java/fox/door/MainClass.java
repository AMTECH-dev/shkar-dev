package fox.door;

import fox.gui.MonitorFrame;


public class MainClass {	
	
	public static void main(String[] args) {
		System.out.println("Launch the programm!");
		
		try {
//			new DataBase();
			new MonitorFrame();
			new SpringEngine();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(114);
		}
	}
}