package fox.doctors;

import fox.Pet;
import fox.gui.MonitorFrame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component("beanDoctor")
public class Doctor {
	private final String name;
	private static boolean isFree = true;

	
	@Autowired
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