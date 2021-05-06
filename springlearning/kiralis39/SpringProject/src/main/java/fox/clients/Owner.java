package fox.clients;

import java.util.List;

import fox.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component("beanOwner")
public class Owner {
	private final String name;
	@Autowired
	private List<Pet> pets;
	
	@Autowired
	public Owner(@Value("${owner.defaultName}") String name, List<Pet> pets) {
		this.name = name;
		this.pets = pets;
		
//		System.out.println("Incoming pets owner '" + name + "' has pets: " + pets.stream().map(Pet::getName).collect(Collectors.joining(",")) + ".");
	}


	public String getName() {return name;}

	public List<Pet> getPets() {return pets;}
}