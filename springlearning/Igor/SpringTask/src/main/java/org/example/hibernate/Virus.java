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
import javax.persistence.Table;

@Entity
@Table(name = "virus")
public class Virus {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "name")
  private String name;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "pvirus",
      joinColumns = @JoinColumn(name = "virusId"),
      inverseJoinColumns = @JoinColumn(name = "petId"))
  List<Pet> petList;

  public void addVirusForPet(Pet p) {
    if (petList == null) {
      petList = new ArrayList<>();
    }
    petList.add(p);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Pet> getPetList() {
    return petList;
  }

  public void setPetList(List<Pet> petList) {
    this.petList = petList;
  }

  public Virus(String name) {
    this.name = name;
  }
}
