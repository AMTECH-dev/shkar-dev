package com.company.Clinic;

import org.springframework.stereotype.Component;

@Component
public class Disease {
    private String name;
    private float damage;

    public Disease() {
        System.out.println("Disease is created");
    }

    public Disease(String name,float damage) {
        System.out.println("Disease is created");
        this.name=name;
        this.damage=damage;
    }

    public void setName(String name) {
        this.name=name;
    }

    public void setDamage(float damage) {
        this.damage=damage;
    }

    @Override
    public String toString() {
        return "Disease:"+name+" Damage:"+damage;
    }

}
