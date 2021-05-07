package org.example.withSpring.animals;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Cat extends Animal implements Pet {

    public Cat(@Value("${animal.catName}") String name, @Value("${animal.catAge}") int age) {
        super(name, age);
        System.out.println("Создана кошка");
    }
}
