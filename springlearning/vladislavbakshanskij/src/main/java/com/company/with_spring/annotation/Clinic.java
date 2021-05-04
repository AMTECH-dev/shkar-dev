package com.company.with_spring.annotation;

import com.company.with_spring.annotation.doctors.Doctor;
import com.company.with_spring.annotation.owners.Owner;
import com.company.with_spring.annotation.pets.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class Clinic {
    private final List<Doctor> doctors;

    public Clinic(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public void work(Owner... owners) {
        work(Arrays.asList(owners));
    }

    public void work(List<Owner> owners) {
        var random = new Random();
        for (Owner owner : owners) {
            var doctor = doctors.get(random.nextInt(doctors.size()));
            System.out.println("DOCTOR " + doctor.getClass().getSimpleName() + " STARTED HEALED");
            if (random.nextBoolean()) {
                doctor.healAllPets(owner);
            } else {
                for (Pet pet : owner.getPets()) {
                    doctor.heal(pet);
                }
            }
        }
    }
}
