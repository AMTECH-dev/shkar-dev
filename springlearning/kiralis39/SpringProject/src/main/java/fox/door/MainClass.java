package fox.door;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import fox.Pet;
import fox.clients.Owner;
import fox.clinics.PetClinic;
import fox.doctors.Doctor;
import fox.pets.Cat;
import fox.pets.Dog;
import fox.pets.Fox;


public class MainClass {

	
	public static void main(String[] args) {
		System.out.println("Launch the programm!");
		
//		handCreate();
		
		springCreate();
	}

	static void springCreate() {		
		try (ClassPathXmlApplicationContext contex = new ClassPathXmlApplicationContext("./resources/appCont.xml")) {

			PetClinic clinic_01 = contex.getBean("beanClinic", PetClinic.class);
			clinic_01.setName("Primary central pet clinic");
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	static void handCreate() {
		PetClinic clinic_01 = new PetClinic("Primary central pet clinic");
		
		Doctor doctor_01 = new Doctor("Doe John");
		Doctor doctor_02 = new Doctor("Agent Smith");
		
		clinic_01.addDoctor(doctor_01);
		clinic_01.addDoctor(doctor_02);
		
		Pet blackCat = new Cat("Barsik", 3.5f, Pet.SEX.FEMA, "BLACK");
		Pet simpleDog = new Dog("Rex", 3.5f, Pet.SEX.MALE, "WHITE");
		Pet cuteFox = new Fox("Uffimia", 3.5f, Pet.SEX.FEMA, "RED");
		
		Owner petOwner_01 = new Owner("mr. Vasya", new Pet[] {blackCat});
		Owner petOwner_02 = new Owner("mr. Kolya", new Pet[] {simpleDog});
		Owner petOwner_03 = new Owner("ms. Victoria", new Pet[] {cuteFox});
		
		doctor_01.addCaresPet(blackCat);
		doctor_01.addCaresPet(simpleDog);
		
		doctor_02.addCaresPet(cuteFox);
	}
}