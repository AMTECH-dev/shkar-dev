package org.example.withoutSpring.doctors;


public class Surgeon extends Doctor {
    public Surgeon(String name, String specialization, int age) {
        super(name, specialization, age);
        System.out.println("Создан врач хирург");
    }
}
