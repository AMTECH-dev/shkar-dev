package org.example.WithSpring.util;

import org.example.WithSpring.doctors.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Clinic {

    @Autowired
    private List<Doctor> doctorList;

    public void addNewDoctor() {

        doctorList.add(new Doctor("Ivan", "LOR", 35) {
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
        System.out.println("пришел трудоустриваться на работу специалист по имени  " + doctorList.get(0).getName() +
                " по направлению " + doctorList.get(0).getSpecialization());

    }

}

