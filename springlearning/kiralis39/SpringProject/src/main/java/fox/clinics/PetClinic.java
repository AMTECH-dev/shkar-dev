package fox.clinics;

import java.util.List;
import fox.Pet;
import fox.doctors.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component("beanClinic")
public class PetClinic {
	private String name;
	private final List<Doctor> doctors;

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
		for (Pet pet : pets) {
			while (!pet.isHealed()) {
				for (Doctor doctor : doctors) {
					if (doctor.isFree()) {
						System.out.println("Doctor '" + doctor.getName() + "' is free, and can get this pet!");
						doctor.setCaresPet(pet);
					} else {
						System.out.println("Doctor '" + doctor.getName() + "' is bisy now...");
					}
				}
			}
		}
	}
}