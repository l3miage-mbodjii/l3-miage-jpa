package fr.uga.im2ag.l3.miage.db.model;

import java.util.List;

import javax.management.Query;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

// TODO ajouter une named query pour une des requêtes à faire dans le repository
@Entity
@Table(name = "Student")
@NamedQueries({

    @NamedQuery(name = "findById", query = "select s from Student s where s.id = :id"),
    @NamedQuery(name = "findStudentHavingGradeAverageAbove", query = "select s from Student s join s.grades g group by s.id having avg(g.value) > :minAverage"),
    @NamedQuery(name="All-Student", query = "select s from Student s")
})
public class Student extends Person {

    private GraduationClass belongTo;
    private List<Grade> grades;

    public GraduationClass getBelongTo() {
        return belongTo;
    }

    public Student setBelongTo(GraduationClass belongTo) {
        this.belongTo = belongTo;
        return this;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public Student setGrades(List<Grade> grades) {
        this.grades = grades;
        return this;
    }
}
