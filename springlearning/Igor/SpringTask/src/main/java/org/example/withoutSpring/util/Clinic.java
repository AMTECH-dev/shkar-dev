package org.example.withoutSpring.util;

import org.example.withoutSpring.doctors.Doctor;
import org.example.withoutSpring.doctors.Lor;
import org.example.withoutSpring.doctors.Surgeon;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


public class Clinic implements AddObject {
    private Lor lor;
    private Surgeon surgeon;


    public Clinic(Lor lor) {
        this.lor = lor;
        System.out.println("Создан конструктор Clinic");
    }

    public Clinic(Surgeon surgeon) {
        this.surgeon = surgeon;
    }


    @Override
    public void addToList() {
        List<Doctor> addDoctors = new ArrayList<>();

        addDoctors.add(new Doctor("Ivan", "LOR", 35) {
            @Override
            public String getName() {
                return super.getName();
            }

            @Override
            public String getSpecialization() {
                return super.getSpecialization();
            }

            @Override
            public int getAge() {
                return super.getAge();
            }
        });
        System.out.println("пришел трудоустриваться на работу специалист по имени  " + addDoctors.get(0).getName() +
                " по направлению " + addDoctors.get(0).getSpecialization());

    }

}

