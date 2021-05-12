package org.example.wishSQL.animals;


abstract public class Animal {

    private String name;
    private int age;

    public Animal(String name, int age) {
        System.out.println("Создан абстрактрый класс Animal");
        this.name = name;
        this.age = age;
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

