package com.company.without_spring.models;

import com.company.without_spring.models.doctors.Doctor;
import com.company.without_spring.models.persons.Person;
import com.company.without_spring.models.pets.Pet;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Clinic {
    private final List<Doctor> doctors;

    public Clinic(Doctor... doctors) {
        this(Arrays.asList(doctors));
    }

    public Clinic(List<Doctor> doctors) {
        System.out.println("Clinic is created");
        this.doctors = doctors;
    }

    public void work(Person... people) {
        work(Arrays.asList(people));
    }

    public void work(List<Person> people) {
        Random random = new Random();

        for (Person person : people) {
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
