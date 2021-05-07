package fox.pets;

import fox.Pet;
import fox.SEX;
import fox.gui.MonitorFrame;


public class AbstractPet<Owner> implements Pet {
	private int id;
	private String name;
	private float age;
	private SEX sex;
	private String color;
	private String comment;
	private Owner owner;

	private int hp = 50;
	private boolean isHealed;

	public AbstractPet() {
		this("noname pet", 1, SEX.MALE, "Iro-iro");
	}

	public AbstractPet(String name, float age, SEX sex, String color) {
		this.sex = sex;
		this.name = name;
		this.age = age;
		this.color = color;
		this.isHealed = false;
		
		System.out.println("A new pet available!\nIt`s a " + getClass().getSimpleName() + " named '" + name + "' (" + age + " y.o.; " + sex.name() + "; " + color + ").");
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
			MonitorFrame.setProgressbarText("Accomplished!");
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