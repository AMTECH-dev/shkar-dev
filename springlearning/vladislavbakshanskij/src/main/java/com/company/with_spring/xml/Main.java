package com.company.with_spring.xml;

import com.company.with_spring.models.Clinic;
import com.company.with_spring.models.persons.Person;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        var context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person sergey = context.getBean("sergey", Person.class);
        Person viktor = context.getBean("viktor", Person.class);
        Clinic clinic = context.getBean("clinic", Clinic.class);
        clinic.work(sergey, viktor);
        context.close();
    }
}
