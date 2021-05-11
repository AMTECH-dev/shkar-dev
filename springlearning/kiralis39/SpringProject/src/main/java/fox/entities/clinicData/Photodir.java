package fox.entities.clinicData;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="photodirs")
public class Photodir {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="dir")
	private String dir;
	
	
	public Photodir() {}
	
	public Photodir(String dir) {
		this.dir = dir;
	}
	
	public Photodir(int id, String dir) {
		this.id = id;
		this.dir = dir;
	}
		
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	
	public String getPath() {return dir;}
	public void setPath(String dir) {this.dir = dir;}


	@Override
	public String toString() {return super.toString();}
}