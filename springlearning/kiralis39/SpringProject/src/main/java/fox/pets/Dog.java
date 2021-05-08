package fox.pets;

import fox.SEX;
import javax.persistence.Entity;

@Entity
public class Dog extends AbstractPet {

	public Dog() {}

	public Dog(String name, float age, SEX sex, String color, String comment, Integer ownerID) {
		super(name, age, sex, color, comment, ownerID);
	}
}