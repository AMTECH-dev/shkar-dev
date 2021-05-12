package com.company.ClinicHibernate.Animals;

import org.springframework.stereotype.Component;

@Component("DogBean")
public class Dog extends Pet {

    public Dog() {
        super();
    }

    public Dog(String name, int age)
    {
        super(name,age);
    }
}
