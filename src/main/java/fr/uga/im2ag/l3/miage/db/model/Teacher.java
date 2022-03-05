
package fr.uga.im2ag.l3.miage.db.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

// TODO ajouter une named query pour une des requêtes à faire dans le repository

//@Entity
@Table(name = "Teacher")
@NamedQueries({
    @NamedQuery(name="get-all-teacher", query = "select t from Teacher t"),
    @NamedQuery(name = "findHeadingGraduationClassByYearAndName", 
    query = "select t from Teacher t join t.heading gc where gc.year = :year and gc.name = :name"),
})
public class Teacher extends Person {
   
    @ManyToOne
    private Subject teaching;
    @OneToMany
    private List<Student> favorites;
    @OneToOne
    private GraduationClass heading;

    public Subject getTeaching() {
        return teaching;
    }

    public Teacher setTeaching(Subject teaching) {
        this.teaching = teaching;
        return this;
    }

    public List<Student> getFavorites() {
        return favorites;
    }

    public Teacher setFavorites(List<Student> favorites) {
        this.favorites = favorites;
        return this;
    }

    public GraduationClass getHeading() {
        return heading;
    }

    public Teacher setHeading(GraduationClass heading) {
        this.heading = heading;
        return this;
    }

}
