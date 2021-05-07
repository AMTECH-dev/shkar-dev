package fox.pets;

import fox.SEX;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;

@Entity
@Component
@Scope("prototype")
public class Fox extends AbstractPet {

	public Fox() {}

	public Fox(@Value("${fox.defaultName}") String name, @Value("${fox.defaultAge}") float age, @Value("${fox.defaultSex}") SEX sex, @Value("${fox.defaultColor}") String color) {
		super(name, age, sex, color);
	}
}