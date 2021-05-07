package fox.tests;

import fox.SEX;
import fox.clients.Owner;

import javax.persistence.*;

@Entity
@Table(name="pets")
public class TestPet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="age")
    private float age;

    @Column(name = "sex")
    @Enumerated(EnumType.STRING)
    private SEX sex;

    @Column(name = "color")
    private String color;
    @Column(name = "comment")
    private String comment;
    @Column(name = "owner")
    private int owner;

    public TestPet() {

    }

    public TestPet(String name, float age, SEX sex, String color, String comment, int owner) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.color = color;
        this.comment = comment;
        this.owner = owner;
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

    public float getAge() {
        return age;
    }

    public void setAge(float age) {
        this.age = age;
    }

    public SEX getSex() {
        return sex;
    }

    public void setSex(SEX sex) {
        this.sex = sex;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "TestPet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", color='" + color + '\'' +
                ", comment='" + comment + '\'' +
                ", owner=" + owner +
                '}';
    }
}
