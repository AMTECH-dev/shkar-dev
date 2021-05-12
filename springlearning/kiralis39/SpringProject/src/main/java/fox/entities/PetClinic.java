package fox.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fox.data.iPet;
import fox.entities.clinicData.Photodir;
import fox.entities.clinicData.Webpage;
import fox.gui.MonitorFrame;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="clinics")
public class PetClinic {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "fiasguid")
	private String fias;
	
	@Column(name = "phone")
	private long phone;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="webpageid")
	private Webpage webpage;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "photodirid")
	private Photodir photodir;
	
	@Column(name = "comment")
	private String comment;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "clidoc", joinColumns = @JoinColumn(name="clinic_id"), inverseJoinColumns = @JoinColumn(name="doctor_id"))
	private List<Doctor> doctors = new ArrayList<Doctor>();	
	
	@Transient
	private final long doctorAwaitingTime = 5_000L;
	@Transient
	private boolean isOpen;
	
	
	public PetClinic() {}

	public PetClinic(String name, String fias, long phone, Webpage webpage, Photodir photodir, String comment) {
		this.name = name;
		this.fias = fias;
		this.phone = phone;
		this.webpage = webpage;
		this.photodir = photodir;
		this.comment = comment;		
	}

	
	public void addDoctor(Doctor doc) {
		doctors.add(doc);
		System.out.println("The clinic '" + name + "' has a new doctor '" + doc.getName() + "'.");
	}

	public void work(iPet... pets) {
		System.out.println("Let`s try to heal the pets: '" + Arrays.asList(pets) + "'...");		
		
		for (iPet pet : pets) {
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

	
	public int getID() {return this.id;}

	public String getName() {return name;}
	public void setName(String clinicName) {this.name = clinicName;}

	public String getFias() {return fias;}
	public void setFias(String fias) {this.fias = fias;}

	public long getPhone() {return phone;}
	public void setPhone(long phone) {this.phone = phone;}

	public Webpage getWebpage() {return webpage;}
	public void setWebpage(Webpage webpage) {this.webpage = webpage;}

	public String getComment() {return comment;}
	public void setComment(String comment) {this.comment = comment;}

	public Photodir getPhotoDir() {return photodir;}
	public void setPhotoDir(Photodir photodir) {this.photodir = photodir;}

	public List<Doctor> getDoctors() {return doctors;}
	public void setDoctors(List<Doctor> doctors) {this.doctors = doctors;}
	public Doctor[] getListOfDoctors() {
		return doctors.toArray(new Doctor[0]);
	}

	public boolean isOpen() {return isOpen;}
	public void setOpen(boolean isOpen) {this.isOpen = isOpen;}

	public long getDoctorAwaitingTime() {return doctorAwaitingTime;}
	
	@Override
	public String toString() {return super.toString();}
}