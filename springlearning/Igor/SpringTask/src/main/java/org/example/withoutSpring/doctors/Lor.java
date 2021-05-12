package org.example.withoutSpring.doctors;


public class Lor extends Doctor {
    public Lor(String name, String specialization, int age) {
        super(name, specialization, age);
        System.out.println("Создан врач ЛОР");


    }

}
