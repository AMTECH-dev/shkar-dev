package org.example.wishSQL.util;

import java.util.Random;

public interface Inspection {

    default boolean inspection() {
        boolean isExistVirus;
        Random random = new Random();
        if (random.nextBoolean() == true) {
            System.out.println("животное реально болеет, нужно лечение");
            isExistVirus = true;
        } else {
            System.out.println("Твой питомец просто выделывается, своди его в KFC");
            isExistVirus = false;
        }
        return isExistVirus;
    }
    default void isOperated(boolean bool){
        if(bool==true){
            System.out.println("Проводим осмотр, надо ли оперирование или амбулатрное лечение");
            Random random = new Random();
            boolean b = random.nextBoolean();
            if (b==true) {
                System.out.println("Срочно оперируем, скальпель, сестра");
            } else {
                System.out.println("попейте витаминки и все будет хорошо");
            }

        }else {
            System.out.println("Заплати за прием и проваливай от сюда");
            System.exit(0);
        }
    }
}
