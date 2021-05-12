package clinic;

import clients.Owner;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clinics")
public class VetClinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String clinicName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clinic")
    private List<Doctor> doctors;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clinic")
    private List<Owner> clients;

    public VetClinic() {
    }

    public VetClinic(String clinicName) {
        this.clinicName = clinicName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public List<Owner> getClients() {
        return clients;
    }

    public void setClients(List<Owner> clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return "VetClinic{" +
                "id=" + id +
                ", clinicName='" + clinicName + '\'' +
                ", doctors=" + doctors +
                ", clients=" + clients +
                '}';
    }
}
