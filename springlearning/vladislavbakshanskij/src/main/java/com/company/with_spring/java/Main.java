package com.company.with_spring.java;

import com.company.with_spring.models.Clinic;
import com.company.with_spring.models.persons.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try (var configApplicationContext = new AnnotationConfigApplicationContext(JavaConfiguration.class)) {
            configApplicationContext.getBean(Clinic.class)
                    .work(new ArrayList<>(configApplicationContext.getBeansOfType(Person.class).values()));

        }
    }
}
