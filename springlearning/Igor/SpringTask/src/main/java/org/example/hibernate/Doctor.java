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
@Table(name = "doctor")
public class Doctor {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "lastname")
  private String lastname;

  @Column(name = "type")
  private String type;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "history",
      joinColumns = @JoinColumn(name = "\"doctorId\""),
      inverseJoinColumns = @JoinColumn(name = "\"ownerId\""))
  List<Owner> ownerList;



  public Doctor(String name, String lastname, String type) {
    this.name = name;
    this.lastname = lastname;
    this.type = type;
  }

  public void addOwner(Owner o) {
    if (ownerList == null) {
      ownerList = new ArrayList<>();
    }
    ownerList.add(o);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public List<Owner> getOwnerList() {
    return ownerList;
  }

  public void setOwnerList(List<Owner> ownerList) {
    this.ownerList = ownerList;
  }

  @Override
  public String toString() {
    return "Doctor{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", lastname='" + lastname + '\'' +
        ", type='" + type + '\'' +
        '}';
  }
}
