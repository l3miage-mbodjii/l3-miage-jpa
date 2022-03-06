package fr.uga.im2ag.l3.miage.db.repository;

import fr.uga.im2ag.l3.miage.db.repository.api.TeacherRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class TeacherTest extends Base {

    TeacherRepository teacherRepository;

    @BeforeEach
    void before() {
        teacherRepository = daoFactory.newTeacherRepository(entityManager);
    }

    @AfterEach
    void after() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
    }

    @Test
    void shouldSaveTeacher()  {
        final var subject = Fixtures.createSubject();
        final var clas = Fixtures.createClass();
        final var teach1 = Fixtures.createTeacher(subject, clas);
        final var teach2 = Fixtures.createTeacher(subject, clas);

        entityManager.getTransaction().begin();

        entityManager.persist(subject);
        entityManager.persist(clas);
        teacherRepository.save(teach1);
        entityManager.getTransaction().commit();
        entityManager.detach(teach1);
     

        teacherRepository.save(teach2);
        entityManager.getTransaction().commit();
        entityManager.detach(teach2);

        var oneTeacher = teacherRepository.findById(teach1.getId());
        assertThat(oneTeacher).isNotNull().isNotSameAs(teach1).isEqualTo(teach1);
        var secondTeacher = teacherRepository.findById(teach2.getId());
        assertThat(secondTeacher).isNotNull().isNotSameAs(teach2).isEqualTo(teach2);
    }

    @Test
    void shouldFindHeadingGraduationClassByYearAndName() {
        // TODO
    }

}
