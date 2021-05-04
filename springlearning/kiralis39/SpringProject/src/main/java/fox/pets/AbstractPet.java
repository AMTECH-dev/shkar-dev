package fox.pets;


import fox.Pet;


public class AbstractPet implements Pet {
	private SEX sex;
	private String name;
	private float age;
	private String color;
	
	
	public AbstractPet(String name, float age, SEX sex, String color) {
		this.sex = sex;
		this.name = name;
		this.age = age;
		this.color = color;
		
		System.out.println("A new pet income! It`s a " + getClass().getSimpleName() + " named '" + name + "' (" + age + " y.o.; " + sex.name() + "; " + color + ").");
	}


	public SEX getSex() {return sex;}
	public void setSex(SEX sex) {this.sex = sex;}

	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	public float getAge() {return age;}
	public void setAge(float age) {this.age = age;}

	public String getColor() {return color;}
	public void setColor(String color) {this.color = color;}	
}