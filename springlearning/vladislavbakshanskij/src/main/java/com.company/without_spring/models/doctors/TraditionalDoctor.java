package com.company.without_spring.models.doctors;

import com.company.without_spring.models.persons.Person;
import com.company.without_spring.models.pets.NakedMoleRat;
import com.company.without_spring.models.pets.Parrot;
import com.company.without_spring.models.pets.Pet;

import java.util.Random;

public class TraditionalDoctor implements Doctor {
    @Override
    public boolean healAllPets(Person p) {
        if (p.getPets() == null) return false;

        boolean isHealed = true;

        for (Pet pet : p.getPets()) {
            if (!(pet instanceof NakedMoleRat) && !(pet instanceof Parrot)) {
                if (!(new Random().nextBoolean())) {
                    System.out.println("В ВАШИХ ЖИВОТНЫХ БЕСЫ ОНИ НЕ ДАЮТ ЛЕЧИТЬ!!");
                    return false;
                }

                isHealed = false;
            } else {
                System.out.println("ЧТО ЭТО ЗА ЖИВОТНОЕ ТАКОЕ: " + pet.getName());
                isHealed = true;
            }
        }

        return isHealed;
    }

    @Override
    public boolean heal(Pet pet) {
        return !(pet instanceof NakedMoleRat) && !(pet instanceof Parrot);
    }

    @Override
    public String getHelp() {
        return "НА НОЧЬ ПРОВЕСТИ 7 КРУГОВ ВОКРУГ ДУБА И ОББЕЖАТЬ РЕКУ С НОЧНЫМИ ФУРИЯМИ!";
    }
}
