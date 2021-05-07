package org.example.withSpring.doctors;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Lor extends Doctor {
    public Lor(@Value("${doctor.lorName}") String name,
               @Value("${doctor.lorSpecialization}") String specialization,
               @Value("${doctor.lorAge}") int age) {
        super(name, specialization, age);
        System.out.println("Создан врач ЛОР");


    }

}
