package org.example.withoutSpring.animals;


abstract public class Animal {

    private String name;
    private int age;
    private String animalType;

    public Animal(String name, int age, String type) {
        this.name = name;
        this.age = age;
        this.animalType = type;
        System.out.println("Создан абстрактрый класс Animal");
    }

    public String getAnimalType() {
        return animalType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

