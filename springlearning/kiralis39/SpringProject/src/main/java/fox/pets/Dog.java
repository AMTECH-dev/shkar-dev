package fox.pets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component("beanDog")
public class Dog extends AbstractPet {
	@Autowired
	public Dog(@Value("${dog.defaultName}") String name, @Value("${dog.defaultAge}") float age, @Value("${dog.defaultSex}") SEX sex, @Value("${dog.defaultColor}") String color) {
		super(name, age, sex, color);
	}
}