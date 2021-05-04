package com.company.without_spring.models.virus;

import com.company.without_spring.models.pets.Pet;

public interface Virus {
    VirusType getType();
    void applyDamage(Pet p);
}
