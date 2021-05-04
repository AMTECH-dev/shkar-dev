package fox.door;

import fox.pets.Cat;
import fox.pets.Dog;
import fox.pets.Fox;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fox.Pet;
import fox.clinics.PetClinic;


public class MainClass {
	private String clinicName;

	public static void main(String[] args) {
		System.out.println("Launch the programm!");

		springCreate();
	}

	static void springCreate() {
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("appCont.xml")) {
			context
				.getBean(PetClinic.class)
				.work(
					context.getBean(Cat.class),
					context.getBean(Dog.class),
					context.getBean(Fox.class));
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}