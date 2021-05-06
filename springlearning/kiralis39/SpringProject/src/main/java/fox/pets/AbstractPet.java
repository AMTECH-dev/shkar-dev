package fox.pets;

import fox.Pet;
import fox.gui.MonitorFrame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component("beanPet")
public class AbstractPet implements Pet {
	private SEX sex;
	private String name;
	private float age;
	private int hp = 50;
	private String color;
	private boolean isHealed;

	@Autowired
	public AbstractPet(@Value("${pet.defaultName}") String name, @Value("${pet.defaultAge}") float age, @Value("${pet.defaultSex}") SEX sex, @Value("${pet.defaultColor}") String color) {
		this.sex = sex;
		this.name = name;
		this.age = age;
		this.color = color;
		
		System.out.println("A new pet available! It`s a " + getClass().getSimpleName() + " named '" + name + "' (" + age + " y.o.; " + sex.name() + "; " + color + ").");
	}


	public SEX getSex() {return sex;}
	public void setSex(SEX sex) {this.sex = sex;}

	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	public float getAge() {return age;}
	public void setAge(float age) {this.age = age;}

	public String getColor() {return color;}
	public void setColor(String color) {this.color = color;}
	
	public boolean isHealed() {return this.isHealed;}
	public void setHealed(boolean isHealed) {
		this.isHealed = isHealed;

		if (this.isHealed) {
			System.out.println("The pet '" + getName() + "' is healed and happy now! =^_^=");
			MonitorFrame.addHealedPetsCollection();
		} else {
			System.out.println("The pet '" + getName() + "' not healed yet! Return later.");

		}
	}
	
	public int getHP() {return hp;}
	public void setHP(int hp) {this.hp = hp;}
	
	@Override
	public String toString() {
		return "Name: " + name + "; age: " + age + "; sex: " + sex + "; color: " + color;		
	}


}