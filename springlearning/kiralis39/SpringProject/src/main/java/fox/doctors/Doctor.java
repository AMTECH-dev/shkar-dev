package fox.doctors;

import fox.Pet;
import fox.SEX;
import fox.gui.MonitorFrame;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;


@Component
@Entity
public class Doctor {
	private int id;
	private String name;
	private int age;
	private String address;
	private long phone;
	private SEX sex;

	private static boolean isFree = true;


	public Doctor() {}

	public Doctor(@Value("${doctor.defaultName}") String name) {
		this.name = name;
	}

	public synchronized void setCaresPet(Pet pet) {
		System.out.println("Pet '" + pet.getName() + "' arrived to dr." + getName());
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				setFree(false);
				Pet currentPet = pet;
				System.out.println("Doctor '" + name + "' has cared on pet '" + currentPet.getName() + "' now.");
				
				while (!currentPet.isHealed()) {
					
					if (currentPet.getHP() >= 100) {
						currentPet.setHP(100);
						System.out.println("Doctor '" + name + "' ends work on pet '" + currentPet + "'!");
						currentPet.setHealed(true);
					} else {
						currentPet.setHP(currentPet.getHP() + 10);
						MonitorFrame.setHealProgressValue(currentPet.getHP(), currentPet.getName());
					}
					
					try {Thread.sleep(1000);
					} catch (Exception e) {
						currentPet.setHealed(false);
						setFree(true);
						MonitorFrame.addFailedPetsCollection();
						System.out.println("Doctor '" + getName() + "' has important stuff. He needs brake. Sorry.");
						Thread.currentThread().interrupt();
						e.printStackTrace();
					}
				}
				
				setFree(true);
			}
		}).start();		
	}

	public String getName() {return this.name;}

	public boolean isFree() {return isFree;}
	public void setFree(boolean isFree) {Doctor.isFree = isFree;}
}