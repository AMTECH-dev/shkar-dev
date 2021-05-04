package fox.doctors;

import fox.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component("beanDoctor")
public class Doctor {
	private final String name;
	private Pet careesPet;
	private static boolean isFree = true;

	@Autowired
	public Doctor(@Value("${doctor.defaultName}") String name) {
		this.name = name;
		
		System.out.println("Income a new doctor '" + this.name + "'.");
	}


	public void setCaresPet(Pet pet) {
		setFree(false);
		
		careesPet = pet;
		System.out.println("Doctor '" + name + "' has cared on pet '" + pet.getName() + "' now.");
		
		pet.setHealed(true);
		setFree(true);
	}


	public String getName() {return this.name;}
	
	public Pet getCaresPet() {return this.careesPet;}


	public boolean isFree() {return isFree;}
	public void setFree(boolean isFree) {Doctor.isFree = isFree;}
}