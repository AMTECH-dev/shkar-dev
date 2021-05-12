package com.company.with_spring.models.virus;

import com.company.with_spring.models.pets.Pet;
import org.springframework.stereotype.Component;


public class Cold implements Virus {
    private float damage;

    public Cold() {
        System.out.println("Cold is created");
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    
    public VirusType getType() {
        return VirusType.FLU;
    }

    
    public void applyDamage(Pet p) {
        System.out.println(p.getName() + " заболел " + getType() + " был нанесен урон в размере " + damage);
    }
}
