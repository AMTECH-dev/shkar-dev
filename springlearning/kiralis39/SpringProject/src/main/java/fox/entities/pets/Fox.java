package fox.entities.pets;

import javax.persistence.Entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fox.data.SEX;
import fox.entities.Pet;

@Entity
@Component
@Scope("prototype")
public class Fox extends Pet {

	public Fox() {}

	public Fox(String name, float age, SEX sex, String color, String comment, Integer ownerID) {
		super(name, age, sex, color, comment, ownerID);
	}
}