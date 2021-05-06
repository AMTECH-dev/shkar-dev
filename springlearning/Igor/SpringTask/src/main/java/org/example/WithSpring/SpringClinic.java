package org.example.WithSpring;

import org.example.WithSpring.animals.Dog;
import org.example.WithSpring.owner.Owner;
import org.example.WithSpring.util.Clinic;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringClinic {


    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new
                ClassPathXmlApplicationContext("applicationContext.xml");
        Dog dog = context.getBean(Dog.class);
        dog.say();
        Owner owner = context.getBean(Owner.class);
        System.out.println(owner.getName());
        Clinic clinic = context.getBean(Clinic.class);
        clinic.addNewDoctor();
        C


        context.close();


    }
}
