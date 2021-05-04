package fox.clients;

import java.util.Arrays;
import java.util.stream.Collectors;

import fox.Pet;

public class Owner {
	private final String name;
	private Pet[] pets;
	
	
	public Owner(String name, Pet[] pets) {
		this.name = name;
		this.pets = pets;
		
		System.out.println("Incoming pets owner '" + name + "' has pets: " + Arrays.asList(pets).stream().map(Pet::getName).collect(Collectors.joining(",")) + ".");
	}


	public String getName() {return name;}

	public Pet[] getPets() {return pets;}
}