package fox.pets;

import fox.SEX;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;

@Entity
@Component
@Scope("prototype")
public class Cat extends AbstractPet {

	public Cat() {}

	public Cat(@Value("${cat.defaultName}") String name, @Value("${cat.defaultAge}") float age, @Value("${cat.defaultSex}") SEX sex, @Value("${cat.defaultColor}") String color) {
		super(name, age, sex, color);
	}
}