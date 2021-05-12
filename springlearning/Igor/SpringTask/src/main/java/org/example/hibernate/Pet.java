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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pet")
public class Pet {

  @Column(name = "name")
  private String name;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "age")
  private int age;

  @Column(name = "\"isVirus\"")
  private boolean isVirus;

  @Column(name = "type")
  private String type;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "pvirus",
      joinColumns = @JoinColumn(name = "petId"),
      inverseJoinColumns = @JoinColumn(name = "virusId"))
  List<Virus>virusList;

  public void addPetToVirus(Virus v) {
    if (virusList == null) {
      virusList = new ArrayList<>();
    }
    virusList.add(v);
  }


  public Pet(String name, int age, boolean isVirus, String type) {
    this.name = name;
    this.age = age;
    this.isVirus = isVirus;
    this.type = type;
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

  public boolean isVirus() {
    return isVirus;
  }

  public void setVirus(boolean virus) {
    isVirus = virus;
  }



  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
