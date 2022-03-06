package fr.uga.im2ag.l3.miage.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "Grade")
@NamedQueries({
    @NamedQuery(name = "getAll",query = "select g from GraduationClass g" ),
    @NamedQuery(name = "findHighestGradesBySubject",query = "select g from GraduationClass g" ),
    @NamedQuery(name = "Grade.findHighestGrades", query = "select g from Grade g where g.value > :limit"),
    @NamedQuery(name = "Grade.findHighestGradeByStudent", query = "select g from Grade g where g.subject = :subject and g.value > :limit")
})

public class Grade {

    private Long id;
    private Subject subject;
    @Column(name = "grade")
    private Float value;
    private Float weight;

    public Long getId() {
        return id;
    }

    public Subject getSubject() {
        return subject;
    }

    public Grade setSubject(Subject subject) {
        this.subject = subject;
        return this;
    }

    public Float getValue() {
        return value;
    }

    public Grade setValue(Float value) {
        this.value = value;
        return this;
    }

    public Float getWeight() {
        return weight;
    }

    public Grade setWeight(Float weight) {
        this.weight = weight;
        return this;
    }
}
