package org.example.hibernate;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "owner")

public class Owner {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "age")
  private int age;

  @Column(name = "phone")
  private Long phone;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "\"ownerId\"")
  private List<Pet> petList;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "history",
      joinColumns = @JoinColumn(name = "\"ownerId\""),
      inverseJoinColumns = @JoinColumn(name = "\"doctorId\""))
  private List<Doctor> doctorList= null;


  public Owner() {
  }

  public Owner(String name, int age, Long phone) {
    this.name = name;
    this.age = age;
    this.phone = phone;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public List<Pet> getPetList() {
    return petList;
  }

  public void setPetList(List<Pet> petList) {
    this.petList = petList;
  }

  public List<Doctor> getDoctorList() {
    return doctorList;
  }

  public void setDoctorList(List<Doctor> doctorList) {
    this.doctorList = doctorList;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public Long getPhone() {
    return phone;
  }

  public void setPhone(Long phone) {
    this.phone = phone;
  }


  public void addPetToOwner(Pet pet) {
    if (petList == null) {
      petList = new ArrayList<>();
    }
    petList.add(pet);
  }

  public void addDoctorToOwner(Doctor d) {
    if (doctorList == null) {
      doctorList = new ArrayList<>();
    }
    doctorList.add(d);
  }
}
