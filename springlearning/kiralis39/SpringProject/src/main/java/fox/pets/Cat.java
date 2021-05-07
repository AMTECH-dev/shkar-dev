package fox.pets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("prototype")
public class Cat extends AbstractPet {
	@Autowired
	public Cat(@Value("${cat.defaultName}") String name, @Value("${cat.defaultAge}") float age,
			   @Value("${cat.defaultSex}") SEX sex, @Value("${cat.defaultColor}") String color) {
		super(name, age, sex, color);
	}
}