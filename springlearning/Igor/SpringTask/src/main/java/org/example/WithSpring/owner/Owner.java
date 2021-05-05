package org.example.WithSpring.owner;

import org.example.WithSpring.animals.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Owner {
    private final Map<String, Animal> ownerAnimals;
    private int ageOwnerDog;
    private String name;
    private Animal animal;

    @Autowired
    public Owner( @Value("${owner.nameOwnerDog}") String name,
                 @Value("${owner.ageOwnerDog}") int ageOwnerDog,
                 List<Animal> animals) {
        this.name = name;
        this.ageOwnerDog = ageOwnerDog;
        Map<String, Animal> map = new HashMap<>();
        for (Animal animal1 : animals) {
            map.put(animal1.getName(), animal1);
        }
        ownerAnimals = map;
        System.out.println("создан конструктор хозяина");
        System.out.println(ownerAnimals);
    }

    public String getName() {
        return name;
    }

}
