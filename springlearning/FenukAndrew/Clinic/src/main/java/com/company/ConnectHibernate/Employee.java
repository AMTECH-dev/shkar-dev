package com.company.ConnectHibernate;

import javax.persistence.*;

//https://habr.com/ru/post/320542/

@Entity
@Table(name="t_Employee")
public class Employee {
    @Id
    @Column(name="c_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="c_name")
    private String name;

    @Column(name="c_age")
    private byte age;

    @Column(name="s_salary")
    private float salary;

    @Column(name="c_department")
    private String department;

    public Employee() {
    }

    public Employee(String name, int age, float salary, String department) {
        this.name = name;
        this.age = (byte) age;
        this.salary = salary;
        this.department=department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "TOSTRING: Employee {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", department='" + department + '\'' +
                '}';
    }
}
