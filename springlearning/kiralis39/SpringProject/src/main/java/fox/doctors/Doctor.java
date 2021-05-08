package fox.doctors;

import fox.Pet;
import fox.SEX;
import fox.gui.MonitorFrame;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Doctor {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "age")
	private int age;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "phone")
	private long phone;
	
	@Column(name = "sex")
	@Enumerated(EnumType.STRING)
	private SEX sex;

	private static boolean isFree = true;


	public Doctor() {}

	public Doctor(String name, int age, String address, long phone, SEX sex) {
		this.name = name;
		this.age = age;
		this.address = address;
		this.phone = phone;
		this.sex = sex;
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
	
	@Override
	public String toString() {
		return super.toString();
	}
}