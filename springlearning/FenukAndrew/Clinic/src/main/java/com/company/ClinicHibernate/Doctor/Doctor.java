package com.company.ClinicHibernate.Doctor;

import com.company.Clinic.Animals.Pet;

public abstract class Doctor {
    private String name;
    public Doctor() {
        System.out.println("Doctor is created");
    }

    public boolean heal(Pet pet) {
        System.out.println("Doctor heal");
        return true;
    }

    public void setName(String name) {
        this.name=name;
        System.out.println("Doctor set name:"+name);
    }

    public String getName() {
        return name;
    };

    @Override
    public String toString() {
        return Doctor.class.getName()+":"+name;
    }

}
