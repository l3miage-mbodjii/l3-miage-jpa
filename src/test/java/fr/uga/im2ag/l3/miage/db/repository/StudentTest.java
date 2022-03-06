package fr.uga.im2ag.l3.miage.db.repository;

import fr.uga.im2ag.l3.miage.db.repository.api.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class StudentTest extends Base {

    StudentRepository studentRepository;

    @BeforeEach
    void before() {
        studentRepository = daoFactory.newStudentRepository(entityManager);
    }

    @AfterEach
    void after() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
    }

    @Test
    void shouldSaveStudent() {
     
            final var ClassOne = Fixtures.createClass();
            final var student = Fixtures.createStudent(ClassOne);
         

            entityManager.getTransaction().begin();
            entityManager.persist(ClassOne);
            studentRepository.save(student);
            entityManager.getTransaction().commit();
            entityManager.detach(student);
            var pStudent1 = studentRepository.findById(student.getId());
            assertThat(pStudent1).isNotNull().isNotSameAs(student).isEqualTo(student);



    }

    @Test
    void shouldFindStudentHavingGradeAverageAbove() {
        // TODO
    }

}
