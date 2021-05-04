package fox.doctors;

import java.util.ArrayList;
import java.util.List;

import fox.Pet;

public class Doctor {
	private final String doctorName;
	private final List<Pet> careesPetList = new ArrayList<Pet>();
	
	
	public Doctor(String doctorName) {
		this.doctorName = doctorName;
		
		System.out.println("Income a new doctor '" + this.doctorName + "'.");
	}


	public void addCaresPet(Pet pet) {
		careesPetList.add(pet);
		System.out.println("Doctor '" + doctorName + "' has cared on pet '" + pet.getName() + "' now.");
	}


	public String getName() {return this.doctorName;}
	
	public Pet[] getCaresPetsList() {return careesPetList.toArray(new Pet[0]);}
}