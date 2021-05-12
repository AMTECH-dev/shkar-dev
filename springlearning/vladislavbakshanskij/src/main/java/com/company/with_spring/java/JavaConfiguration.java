package com.company.with_spring.java;

import com.company.with_spring.models.Clinic;
import com.company.with_spring.models.doctors.Doctor;
import com.company.with_spring.models.doctors.TraditionalDoctor;
import com.company.with_spring.models.doctors.UniversalDoctor;
import com.company.with_spring.models.persons.CatOwner;
import com.company.with_spring.models.persons.Owner;
import com.company.with_spring.models.persons.Person;
import com.company.with_spring.models.pets.*;
import com.company.with_spring.models.virus.Cold;
import com.company.with_spring.models.virus.Plague;
import com.company.with_spring.models.virus.Virus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class JavaConfiguration {
    @Bean
    public Virus plague() {
        return new Plague();
    }

    @Bean
    public Virus cold() {
        return new Cold();
    }

    @Bean
    public Pet dog() {
        return new Dog(plague());
    }

    @Bean
    public Pet cat() {
        return new Cat(cold());
    }

    @Bean
    public Pet parrot() {
        return new Parrot(cold());
    }

    @Bean
    public Pet nakedMoleRat() {
        return new NakedMoleRat();
    }

    @Bean
    public Person owner(List<Pet> pets) {
        var owner = new Owner();
        owner.setPets(pets);
        return owner;
    }

    @Bean
    public Person oleg(@Qualifier("cat") List<Pet> cats) {
        return new CatOwner(cats);
    }

    @Bean
    public Person sergey(List<Pet> cats) {
        return new CatOwner(cats);
    }

    @Bean
    public Doctor traditionalDoctor() {
        return new TraditionalDoctor();
    }

    @Bean
    public Doctor universalDoctor() {
        return new UniversalDoctor();
    }

    @Bean
    public Clinic clinic(List<Doctor> doctors) {
        var clinic = new Clinic();
        clinic.setDoctors(doctors);
        return clinic;
    }
}
