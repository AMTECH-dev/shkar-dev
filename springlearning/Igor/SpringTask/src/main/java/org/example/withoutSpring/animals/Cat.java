package org.example.withoutSpring.animals;

//@Component
public class Cat extends Animal implements Pet {

    public Cat(String name, int age, String type) {
        super(name, age, type);
        System.out.println("Создана кошка");
    }
}
