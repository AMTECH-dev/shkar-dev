package fox.clinics;

import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import fox.Pet;
import fox.doctors.Doctor;
import fox.gui.MonitorFrame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;

@Entity
public class PetClinic {
	private int id;
	private String name;
	private String fias;
	private long phone;
	private Integer urlIndex;
	private String comment;
	private Integer photoDirIndex;

	private List<Doctor> doctors;
	private final long doctorAwaitingTime = 5_000L;

	public PetClinic() {

	}

	public PetClinic(ResultSet rs) throws Exception {
		this(
				rs.getString("name"),
				rs.getString("fiasGUID"),
				rs.getLong("phoneNumber"),
				rs.getInt("urlAddress"),
				rs.getString("comment"),
				rs.getInt("photoId")
		);
	}

	public PetClinic(String name, String fias, long phone, Integer urlIndex, String comment, Integer photoDirIndex) {
		this.name = name;
		this.fias = fias;
		this.phone = phone;
		this.urlIndex = urlIndex;
		this.comment = comment;
		this.photoDirIndex = photoDirIndex;
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

	public Integer getUrlIndex() {return urlIndex;}
	public void setUrlIndex(Integer urlIndex) {this.urlIndex = urlIndex;}

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
}