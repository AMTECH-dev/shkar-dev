package com.company.with_spring.models;

import com.company.with_spring.models.doctors.Doctor;
import com.company.with_spring.models.persons.Person;
import com.company.with_spring.models.pets.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Clinic {
    private List<Doctor> doctors;

    public Clinic() {
        System.out.println("Clinic is created");
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public void work(Person... people) {
        work(Arrays.asList(people));
    }

    public void work(List<Person> people) {
        Random random = new Random();

        for (Person person : people) {
            if (person.getPets() == null) continue;
            Doctor doctor = doctors.get(random.nextInt(doctors.size()));
            System.out.println("Сейчас лечит: " + doctor.getClass().getSimpleName());
            if (random.nextBoolean()) {
                doctor.healAllPets(person);
            } else {
                for (Pet pet : person.getPets()) {
                    doctor.heal(pet);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
