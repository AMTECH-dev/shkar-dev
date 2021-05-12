package com.company.ClinicHibernate;

import com.company.Clinic.Doctor.Doctor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Clinic {
    private List<Doctor> doctors;

    private List<Master> patients=new ArrayList<Master>();

    int queryPatient=0;

    public Clinic(List<Doctor> doctors) {
        this.doctors=doctors;
        System.out.println("Clinic is created");
    }

    public Clinic() {
        this(new ArrayList<Doctor>());
    }

    public Clinic(Doctor doctor) {
        this();
        doctors.add(doctor);
    }

    public void setDoctor(Doctor doctor) {
        System.out.println("Clinic: set doctor");
        this.doctors.add(doctor);
    }

    public boolean setReception(Master patient) {
        patients.add(patient);
        System.out.println("Patient "+patient.getName()+" to doctor "+doctors.get(queryPatient).getName());
        queryPatient=queryPatient % doctors.size();
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append(">>>>>Clinic<<<<<\n");
        sb.append(">>>>>DOCTORS:\n");
        for(Doctor doctor : doctors) {
            sb.append(doctor.toString());sb.append("\n");
        }
        sb.append("<<<<<DOCTORS\n");
        sb.append(">>>>>MASTERS:\n");
        for(Master patient : patients) {
            sb.append(patient.toString());sb.append("\n");
        }
        sb.append("<<<<<MASTERS\n");
        return sb.toString();
    }

}
