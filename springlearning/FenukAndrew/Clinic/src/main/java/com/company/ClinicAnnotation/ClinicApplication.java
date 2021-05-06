package com.company.ClinicAnnotation;

import com.company.Clinic.Animals.Pet;
import com.company.Clinic.Doctor.Doctor;
import com.company.ClinicAnnotation.Animals.Cat;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//@SpringBootApplication
public class ClinicApplication {

	public static void main(String[] args) {
		var applicationContext=new ClassPathXmlApplicationContext("classpath*:clinic-bean-annotation.xml");

		/*Disease disease=applicationContext.getBean("disease",Disease.class);
		System.out.println(disease.toString());*/

		/*Cat cat=applicationContext.getBean("cat",Cat.class);
		System.out.println(cat.toString());*/

		/*Master master=applicationContext.getBean("master",Master.class);
		System.out.println(master.toString());*/

		Clinic clinic=applicationContext.getBean("clinic",Clinic.class);
		System.out.println(clinic.toString());

		applicationContext.close();

	}

}
