package com.company.Clinic;

import com.company.Clinic.Animals.Pet;
import com.company.Clinic.Doctor.Doctor;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//@SpringBootApplication
public class ClinicApplication {

	public static void main(String[] args) {
		var applicationContext=new ClassPathXmlApplicationContext("classpath*:clinic-bean.xml");//ApplicationContext
		/*System.out.println("S:"+applicationContext.getBeanDefinitionCount());
		for(String s : 	applicationContext.getBeanDefinitionNames()) {
			System.out.println("S:"+s);
		}*/

		/*Pet pet=applicationContext.getBean("Dog1",Pet.class);
		pet.test();

		Doctor doctor=(Doctor)applicationContext.getBean("Doctor1");

		Master master=(Master)applicationContext.getBean("man");
		System.out.println(master.toString());*/

		Clinic clinic=applicationContext.getBean("PrimaryClinic",Clinic.class);
		System.out.println(clinic.toString());

		applicationContext.close();

	}

}
