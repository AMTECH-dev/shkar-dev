package fox.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import fox.data.SEX;


@Entity
@Table(name="clients")
public class Owner {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "age")
	private int age;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "phone")
	private long phone;
	
	@Column(name = "sexid")
	private SEX sex;
	
	@Column(name = "comment")
	private String comment;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
	private Set<Pet> pets = new HashSet<Pet>();
	

	public Owner() {}

	public Owner(String name, int age, String address, long phone, SEX sex, String comment) {
		this.name = name;
		this.age = age;
		this.address = address;
		this.phone = phone;
		this.sex = sex;
		this.comment = comment;
	}

	public int getId() {return id;}
	public void setId(int id) {this.id = id;}

	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	public int getAge() {return age;}
	public void setAge(int age) {this.age = age;}

	public String getAddress() {return address;}
	public void setAddress(String address) {this.address = address;}

	public long getPhone() {return phone;}
	public void setPhone(long phone) {this.phone = phone;}

	public SEX getSex() {return sex;}
	public void setSex(SEX sex) {this.sex = sex;}

	public String getComment() {return comment;}
	public void setComment(String comment) {this.comment = comment;}

	public boolean addPet(Pet pet) {
		if (pet != null) {
			pets.add(pet);
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {return getName();}
}