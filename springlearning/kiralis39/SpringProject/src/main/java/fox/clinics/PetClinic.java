package fox.clinics;

import java.util.ArrayList;
import java.util.List;

import fox.doctors.Doctor;


public class PetClinic {
	private final String clinicName;
	private final List<Doctor> doctors = new ArrayList<Doctor>();
	
	
	public PetClinic(String clinicName) {
		this.clinicName = clinicName;
		
		System.out.println("Was created new clinic '" + this.clinicName + ".");
	}

	
	public String getName() {
		return clinicName;
	}


	public void addDoctor(Doctor doc) {
		System.out.println("The clinic '" + clinicName + "' has a new doctor '" + doc.getName() + "'.");
		doctors.add(doc);
	}
	
	public Doctor[] getListOfDoctors() {return doctors.toArray(new Doctor[0]);}
}