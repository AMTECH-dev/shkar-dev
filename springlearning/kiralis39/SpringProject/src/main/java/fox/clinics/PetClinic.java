package fox.clinics;

import java.util.Arrays;
import java.util.List;
import fox.Pet;
import fox.doctors.Doctor;
import fox.gui.MonitorFrame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class PetClinic {
	private String name;
	private final List<Doctor> doctors;
	private final long doctorAwaitingTime = 5_000L;
	
	
	@Autowired
	public PetClinic(@Value("${clinic.defaultName}") String name, List<Doctor> doctors) {
		this.name = name;
		this.doctors = doctors;
		System.out.println("Was created new clinic '" + this.name + "'.");
	}

	
	public String getName() {return name;}


	public void addDoctor(Doctor doc) {
		System.out.println("The clinic '" + name + "' has a new doctor '" + doc.getName() + "'.");
		doctors.add(doc);
	}
	
	public Doctor[] getListOfDoctors() {return doctors.toArray(new Doctor[0]);}


	public void setName(String clinicName) {this.name = clinicName;}


	public void work(Pet... pets) {
		System.out.println("Let`s try to heal the pets: '" + Arrays.asList(pets) + "'...");		
		
		for (Pet pet : pets) {
			MonitorFrame.setHealProgressValue(0, pet.getName());
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					long timeStamp = System.currentTimeMillis();
					
					while (!pet.isHealed() && (timeStamp - System.currentTimeMillis() < doctorAwaitingTime)) {
						for (Doctor doctor : doctors) {
							if (doctor.isFree()) {
								System.out.println("Doctor '" + doctor.getName() + "' is free, and can get this pet!");
								doctor.setCaresPet(pet);
							}
						}
						
						try {Thread.sleep(250);
						} catch (InterruptedException e) {
							System.out.println("Pet '" + pet + "' fills better suddenly.. Ok than.");
							Thread.currentThread().interrupt();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
					if (!pet.isHealed()) {
						System.out.println("Sorry! Doctor '" + getName() + "' can`t get Your per for a while... Try later.");
					}
				}
			}).start();			
			
		}
	}
}