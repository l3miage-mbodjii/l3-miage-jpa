package fr.uga.im2ag.l3.miage.db.repository.impl;

import fr.uga.im2ag.l3.miage.db.repository.api.StudentRepository;
import fr.uga.im2ag.l3.miage.db.model.Student;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryImpl extends BaseRepositoryImpl implements StudentRepository {

  
    /**
     * Build a base repository
     *
     * @param entityManager the entity manager
     */
    public StudentRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void save(Student entity) {
        entityManager.persist(entity);

    }

    @Override
    public void delete(Student entity) {
        entityManager.remove(entity);

    }

    @Override
    public Student findById(Long id) {

        return entityManager.find(Student.class,id);
    }

    @Override
    public List<Student> getAll() {

        List<Student> liste = new ArrayList<>();
        liste = entityManager.createNamedQuery("All-Student",Student.class)
        .getResultList();
        return liste;
    }

    @Override
    public List<Student> findStudentHavingGradeAverageAbove(float minAverage) {
        return entityManager.createNamedQuery("findStudentHavingGradeAverageAbove", Student.class)
                .setParameter("minAverage", minAverage)
                .getResultList();
    }
}
