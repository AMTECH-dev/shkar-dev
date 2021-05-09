package fox.entities.pets;

import javax.persistence.Entity;

import fox.data.SEX;
import fox.entities.Pet;

@Entity
public class Dog extends Pet {

	public Dog() {}

	public Dog(String name, float age, SEX sex, String color, String comment, Integer ownerID) {
		super(name, age, sex, color, comment, ownerID);
	}
}