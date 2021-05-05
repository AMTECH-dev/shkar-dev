package org.example.withoutSpring.animals;

public class Dog extends Animal implements Pet {

    public Dog(String name, int age, String type) {
        super(name, age, type);
        System.out.println("Создана собака");
    }
}
