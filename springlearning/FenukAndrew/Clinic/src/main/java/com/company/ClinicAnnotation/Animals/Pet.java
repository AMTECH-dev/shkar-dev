package com.company.ClinicAnnotation.Animals;

import com.company.Clinic.Disease;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;

public abstract class Pet {
    private String name;
    private int age;
    private List<Disease> diseases;

    public Pet() {
        System.out.println("Pet is created:"+Pet.class.getName());
        this.diseases = new ArrayList<Disease>();
    }

    public Pet(String name,int age) {
        this.name=name;
        this.age=age;
        System.out.println(Pet.class.getName()+" is created");
        this.diseases = new ArrayList<Disease>();
    }

    public void setName(String name) {
        this.name=name;
        System.out.println(Pet.class.getName()+" set name:"+name);
    }

    public void setAge(int age) {
        this.age=age;
        System.out.println(Pet.class.getName()+name+" set age:"+age);
    }

    public void setDisease(Disease disease) {
        System.out.println(Pet.class.getName()+": set disease");
        this.diseases.add(disease);
    }

    public void setDiseases(List<Disease> diseases) {
        System.out.println(Pet.class.getName()+": set diseases");
        this.diseases=diseases;
    }

    public void test() {
        System.out.println("Pet is tested");
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append(Pet.class.getName()+":"+name+" age:"+age+"\n");
        for(Disease disease : diseases) {
            sb.append(disease.toString());sb.append("\n");
        }
        return sb.toString();
    }
}
