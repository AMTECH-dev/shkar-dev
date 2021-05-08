package fox.pets;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import fox.Pet;
import fox.SEX;
import fox.gui.MonitorFrame;


@Entity
@Table(name="pets")
public class AbstractPet implements Pet {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
	private int id;
	
	@Column(name="name")
    private String name;
	
    @Column(name="age")
    private float age;
    
    @Column(name = "sex")
    @Enumerated(EnumType.STRING)
    private SEX sex;
    
    @Column(name = "color")
    private String color;
    
    @Column(name = "comment")
    private String comment;
    
    @Column(name = "ownerid")
    private Integer ownerid;
    
    @Transient
	private int hp;
    @Transient
	private boolean isHealed;

	
	public AbstractPet() {
		this("noname pet", 1, SEX.MALE, "Iro-iro", "no comments", null);
	}

	public AbstractPet(String name, float age, SEX sex, String color, String comment, Integer ownerid) {
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.color = color;
		this.comment = comment;
		this.ownerid = ownerid;
		
		this.hp = 50;
		this.isHealed = false;
		
		System.out.println("A new pet available!\nIt`s a " + toString());
	}

	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	public float getAge() {return age;}
	public void setAge(float age) {this.age = age;}
	
	public SEX getSex() {return sex;}
	public void setSex(SEX sex) {this.sex = sex;}

	public String getColor() {return color;}
	public void setColor(String color) {this.color = color;}
	
	public String getComment() {return comment;}
	public void setComment(String comment) {this.comment = comment;}
	
	public Integer getOwnerID() {return ownerid;}
	public void setOwnerID(Integer ownerid) {this.ownerid = ownerid;}
	
		
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
		return getClass().getSimpleName() + " named '" + name + "' (" + age + " y.o.; " + sex.name() + "; " + color + "; [" + comment + "]) Owner ID: " + ownerid + ".";		
	}
}