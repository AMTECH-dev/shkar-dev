package fox.entities.clinicData;

import java.net.URL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="webpages")
public class Webpage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="url")
	private URL url;
	
	
	public Webpage() {}
	
	public Webpage(URL url) {
		this.url = url;
	}
	
	public Webpage(int id, URL url) {
		this.id = id;
		this.url = url;
	}
	

	public int getId() {return id;}
	public void setId(int id) {this.id = id;}

	public URL getUrl() {return url;}
	public void setUrl(URL url) {this.url = url;}
	
	@Override
	public String toString() {return super.toString();}
}