package fox.pets;

import fox.SEX;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;

@Entity
@Component
@Scope("prototype")
public class Dog extends AbstractPet {

	public Dog() {}

	public Dog(@Value("${dog.defaultName}") String name, @Value("${dog.defaultAge}") float age, @Value("${dog.defaultSex}") SEX sex, @Value("${dog.defaultColor}") String color) {
		super(name, age, sex, color);
	}
}