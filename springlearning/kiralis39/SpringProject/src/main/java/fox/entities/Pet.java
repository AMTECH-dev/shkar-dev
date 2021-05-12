package fox.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import fox.data.iPet;
import fox.data.SEX;
import fox.gui.MonitorFrame;


@Entity
@Table(name="pets")
public class Pet implements iPet {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
	private int id;
	
	@Column(name="name")
    private String name;
	
    @Column(name="age")
    private float age;
    
    @Column(name = "sexid")
    private SEX sex;
    
    @Column(name = "color")
    private String color;
    
    @Column(name = "comment")
    private String comment;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ownerid")
    private Owner owner;
    
    @Transient
	private int hp;
    @Transient
	private boolean isHealed;

	
	public Pet() {
		this("noname pet", 1, SEX.MALE, "Iro-iro", "no comments", null);
	}

	public Pet(String name, float age, SEX sex, String color, String comment, Owner owner) {
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.color = color;
		this.comment = comment;
		this.owner = owner;
		
		this.hp = 50;
		this.isHealed = false;
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
	
	public Owner getOwner() {return owner;}
	public void setOwner(Owner owner) {this.owner = owner;}
	
		
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
		return getClass().getSimpleName() + " named '" + name + "' (" + age + " y.o.; " + sex.name() + "; " + color + "; [" + comment + "]) Owner: " + owner + ".";		
	}
}