package com.company.with_spring.models.virus;

import com.company.with_spring.models.pets.Pet;
import org.springframework.stereotype.Component;

@Component
public class Plague implements Virus {
    private int damage;

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public VirusType getType() {
        return VirusType.PLAGUE;
    }

    @Override
    public void applyDamage(Pet p) {
        System.out.println(p.getName() + " заболел " + getType() + " был нанесен урон в размере " + damage);
    }
}
