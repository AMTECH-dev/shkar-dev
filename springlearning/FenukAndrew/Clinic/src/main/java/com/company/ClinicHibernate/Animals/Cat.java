package com.company.ClinicHibernate.Animals;

import com.company.Clinic.Disease;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;

@Component
@Entity
@Table(name="t_Pet")

public class Cat extends Pet {
    public Cat() {
        super();
    }
    public Cat(String name,int age) {
        super(name,age);
    }

}
