package fox.entities;

import fox.data.iPet;
import fox.data.SEX;
import fox.gui.MonitorFrame;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="doctors")
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
	private SEX sex;

	@Transient
	private static boolean isFree = true;


	public Doctor() {}

	public Doctor(String name, int age, String address, long phone, SEX sex) {
		this.name = name;
		this.age = age;
		this.address = address;
		this.phone = phone;
		this.sex = sex;
	}

	public synchronized void setCaresPet(iPet pet) {
		System.out.println("Pet '" + pet.getName() + "' arrived to dr." + getName());
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				setFree(false);
				iPet currentPet = pet;
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
		return getClass().getSimpleName() + " " + name + ": age: " + age + "; address: " + address + "; phone: " + phone + "; sex: " + sex;
	}
}