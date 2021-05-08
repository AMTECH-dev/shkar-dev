package fox.clinics;

import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;

import fox.Pet;
import fox.doctors.Doctor;
import fox.gui.MonitorFrame;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class PetClinic {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "fiasGUID")
	private String fias;
	
	@Column(name = "phone")
	private long phone;
	
	@Column(name = "webpageid")
	private Integer webpageIndex;
	
	@Column(name = "photodirid")
	private Integer photoDirIndex;
	
	@Column(name = "comment")
	private String comment;

	private List<Doctor> doctors;
	private final long doctorAwaitingTime = 5_000L;

	
	public PetClinic() {

	}

	public PetClinic(ResultSet rs) throws Exception {
		this(
				rs.getString("name"),
				rs.getString("fiasGUID"),
				rs.getLong("phone"),
				rs.getInt("webpageid"),
				rs.getInt("photodirid"),
				rs.getString("comment")
		);
	}

	public PetClinic(String name, String fias, long phone, Integer webpageIndex, Integer photoDirIndex, String comment) {
		this.name = name;
		this.fias = fias;
		this.phone = phone;
		this.webpageIndex = webpageIndex;
		this.photoDirIndex = photoDirIndex;
		this.comment = comment;
	}

	public void addDoctor(Doctor doc) {
		System.out.println("The clinic '" + name + "' has a new doctor '" + doc.getName() + "'.");
		doctors.add(doc);
	}

	public void work(Pet... pets) {
		System.out.println("Let`s try to heal the pets: '" + Arrays.asList(pets) + "'...");		
		
		for (Pet pet : pets) {
			MonitorFrame.setHealProgressValue(0, pet.getName());
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					long timeStamp = System.currentTimeMillis();
					
					while (!pet.isHealed() && (timeStamp - System.currentTimeMillis() < doctorAwaitingTime)) {
						for (Doctor doctor : doctors) {
							if (doctor.isFree()) {
								System.out.println("Doctor '" + doctor.getName() + "' is free, and can get this pet!");
								doctor.setCaresPet(pet);
							}
						}
						
						try {Thread.sleep(250);
						} catch (InterruptedException e) {
							System.out.println("Pet '" + pet + "' fills better suddenly.. Ok than.");
							Thread.currentThread().interrupt();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
					if (!pet.isHealed()) {
						System.out.println("Sorry! Doctor '" + getName() + "' can`t get Your per for a while... Try later.");
					}
				}
			}).start();			
			
		}
	}


	public String getName() {return name;}
	public void setName(String clinicName) {this.name = clinicName;}

	public String getFias() {return fias;}
	public void setFias(String fias) {this.fias = fias;}

	public long getPhone() {return phone;}
	public void setPhone(long phone) {this.phone = phone;}

	public Integer getUrlIndex() {return webpageIndex;}
	public void setUrlIndex(Integer webpageIndex) {this.webpageIndex = webpageIndex;}

	public String getComment() {return comment;}
	public void setComment(String comment) {this.comment = comment;}

	public Integer getPhotoDirIndex() {return photoDirIndex;}
	public void setPhotoDirIndex(Integer photoDirIndex) {this.photoDirIndex = photoDirIndex;}

	public List<Doctor> getDoctors() {return doctors;}
	public void setDoctors(List<Doctor> doctors) {this.doctors = doctors;}
	public Doctor[] getListOfDoctors() {
		return doctors.toArray(new Doctor[0]);
	}

	public long getDoctorAwaitingTime() {return doctorAwaitingTime;}
	
	@Override
	public String toString() {
		return super.toString();
	}
}