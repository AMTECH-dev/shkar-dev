package clients;

import clients.pets.Pet;
import clinic.VetClinic;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "owners")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Pet> pets;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "clinic_id")
    private VetClinic clinic;

    public Owner() {
    }

    public Owner(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VetClinic getClinic() {
        return clinic;
    }

    public void setClinic(VetClinic clinic) {
        this.clinic = clinic;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
