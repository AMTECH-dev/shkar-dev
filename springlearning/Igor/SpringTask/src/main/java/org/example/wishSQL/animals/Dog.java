package org.example.wishSQL.animals;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Dog extends Animal implements Pet {
    public Dog(@Value("${animal.dogName}") String name, @Value("${animal.dorAge}") int age) {
        super(name, age);
        System.out.println("Создана собака: " + name + " age: " + age);
    }

}
