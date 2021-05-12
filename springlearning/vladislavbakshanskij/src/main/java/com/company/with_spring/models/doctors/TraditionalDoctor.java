package com.company.with_spring.models.doctors;

import com.company.with_spring.models.persons.Person;
import com.company.with_spring.models.pets.NakedMoleRat;
import com.company.with_spring.models.pets.Parrot;
import com.company.with_spring.models.pets.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Random;


public class TraditionalDoctor implements Doctor {
    
    public boolean healAllPets(Person p) {
        if (p.getPets() == null) return false;

        boolean isHealed = true;

        for (Pet pet : p.getPets()) {
            if (!(pet instanceof NakedMoleRat) && !(pet instanceof Parrot)) {
                if (!(new Random().nextBoolean())) {
                    System.err.println("В ВАШИХ ЖИВОТНЫХ БЕСЫ ОНИ НЕ ДАЮТ ЛЕЧИТЬ!!");
                    return false;
                }

                isHealed = true;
            } else {
                System.err.println("ЧТО ЭТО ЗА ЖИВОТНОЕ ТАКОЕ: " + pet.getName());
                isHealed = false;
            }
        }

        return isHealed;
    }

    
    public boolean heal(Pet pet) {
        return !(pet instanceof NakedMoleRat) && !(pet instanceof Parrot);
    }

    
    public String getHelp() {
        return "НА НОЧЬ ПРОВЕСТИ 7 КРУГОВ ВОКРУГ ДУБА И ОББЕЖАТЬ РЕКУ С НОЧНЫМИ ФУРИЯМИ!";
    }
}
