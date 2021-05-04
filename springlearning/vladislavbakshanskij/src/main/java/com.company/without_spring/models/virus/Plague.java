package com.company.without_spring.models.virus;

import com.company.without_spring.models.pets.Pet;

public class Plague implements Virus {
    private final float damage;

    public Plague(float damage) {
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
