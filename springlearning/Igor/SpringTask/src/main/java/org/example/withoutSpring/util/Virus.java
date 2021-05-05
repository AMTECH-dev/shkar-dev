package org.example.withoutSpring.util;

import java.util.Random;

public interface Virus {

    default void virus() {
         Random random = new Random();
        if (random.nextBoolean() == true) {
            System.out.println("животное реально болеет, нужно лечение");
        } else {
            System.out.println("Твой питомец просто выделывается, своди его в KFC");
        }
    }
}
