package fr.uga.im2ag.l3.miage.db.model;

import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.*;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
@DiscriminatorColumn(name = "type")
public abstract class Person {
    @Id
    @GeneratedValue
    private Long id;
    private Gender gender;
    private String firstName;
    private String lastName;
    private Date birth;

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public Person setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Person setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Person setId(Long id) {
        this.id = id;
        return this;
    }

    public Date getBirth() {
        return birth;
    }

    public Person setBirth(Date birth) {
        this.birth = birth;
        return this;
    }

    public Gender getGender() {
        return gender;
    }

    public Person setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public enum Gender {
        FEMALE, MALE, FLUID
    }

}
