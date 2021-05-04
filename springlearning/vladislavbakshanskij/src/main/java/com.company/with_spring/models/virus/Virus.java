package com.company.with_spring.models.virus;

import com.company.with_spring.models.pets.Pet;

public interface Virus {
    VirusType getType();
    void applyDamage(Pet p);
}
