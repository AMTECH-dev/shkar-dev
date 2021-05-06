package org.example.withoutSpring.doctors;


import org.example.withoutSpring.util.Virus;

abstract public class Doctor implements Virus {

    private String name;
    private String specialization;
    private int age ;

    public Doctor(String name, String specialization, int age) {
        this.name = name;
        this.specialization = specialization;
        this.age = age;
        System.out.println("Создан абстрактрый класс Doctor");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
