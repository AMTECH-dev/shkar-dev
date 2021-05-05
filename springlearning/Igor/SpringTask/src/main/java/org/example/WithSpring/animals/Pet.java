package org.example.WithSpring.animals;

public interface Pet {
    default void say() {
        System.out.println("Чего то плохо себя чуствую, поехали ка в больничку");
    }
}
