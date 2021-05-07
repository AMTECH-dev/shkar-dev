package org.example.wishSQL.doctors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Surgeon extends Doctor {
    public Surgeon(@Value("${doctor.surgeonName}") String name,
                   @Value("${doctor.surgeonSpecialization}") String specialization,
                   @Value("${doctor.surgeonAge}") int age) {
        super(name, specialization, age);
        System.out.println("Создан врач хирург");
    }
}
