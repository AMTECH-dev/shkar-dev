package fox.door;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;

import fox.gui.MonitorFrame;


public class MainClass {
	private static MonitorFrame monitor;
	
	
	public static void main(String[] args) {
		System.out.println("Launch the programm!");

		try {
			new DataBase();
			monitor = new MonitorFrame(); // open monitor frame
			new SpringEngine(); // create the Spring-context
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}