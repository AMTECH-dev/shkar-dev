package fox.clients;

import java.util.List;

import fox.Pet;
import fox.SEX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;

@Entity
@Component
public class Owner {
	private int id;
	private String name;
	private int age;
	private String address;
	private long phone;
	private SEX sex;
	private String comment;

	private List<Pet> pets;
	

	public Owner() {}

	public Owner(@Value("${owner.defaultName}") String name, List<Pet> pets) {
		this.name = name;
		this.pets = pets;
	}

	public String getName() {return name;}

	public List<Pet> getPets() {return pets;}
}