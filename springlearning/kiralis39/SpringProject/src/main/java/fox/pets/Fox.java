package fox.pets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component("beanFox")
public class Fox extends AbstractPet {
	@Autowired
	public Fox(@Value("${fox.defaultName}") String name, @Value("${fox.defaultAge}") float age, @Value("${fox.defaultSex}") SEX sex, @Value("${fox.defaultColor}") String color) {
		super(name, age, sex, color);
	}
}