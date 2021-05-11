package clients.pets;

import clients.Owner;

public class Dog extends Pet {

    public Dog() {
    }

    public Dog(String name, int age, Owner owner, String illness) {
        super(name, age, owner, illness);
    }
}
