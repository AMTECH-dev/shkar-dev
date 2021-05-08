package fox.clients;

import fox.SEX;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Component
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
	
	@Column(name = "sex")
	@Enumerated(EnumType.STRING)
	private SEX sex;
	
	@Column(name = "comment")
	private String comment;
	

	public Owner() {}

	public Owner(String name, int age, String address, long phone, SEX sex, String comment) {
		this.name = name;
		this.age = age;
		this.address = address;
		this.phone = phone;
		this.sex = sex;
		this.comment = comment;
	}

	public String getName() {return name;}
	
	@Override
	public String toString() {
		return super.toString();
	}
}