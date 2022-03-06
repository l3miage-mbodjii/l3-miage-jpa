package fr.uga.im2ag.l3.miage.db.repository;

import fr.uga.im2ag.l3.miage.db.model.Teacher;
import fr.uga.im2ag.l3.miage.db.repository.api.SubjectRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;

class SubjectTest extends Base {

    SubjectRepository subjectRepository;

    @BeforeEach
    void before() {
        subjectRepository = daoFactory.newSubjectRepository(entityManager);
    }

    @AfterEach
    void after() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
    }

    @Test
    void shouldSaveSubject() {

        final var subject = Fixtures.createSubject();

        entityManager.getTransaction().begin();
        subjectRepository.save(subject);
        entityManager.getTransaction().commit();
        entityManager.detach(subject);

        var pSubject = subjectRepository.findById(subject.getId());
        assertThat(pSubject).isNotNull().isNotSameAs(subject);
        assertThat(pSubject.getName()).isEqualTo(subject.getName());

    }

    @Test
    void shouldFindTeachersForSubject() {

        final var student = Fixtures.createStudent(Fixtures.createClass());
        final var subject = Fixtures.createSubject();
        final var teacher = Fixtures.createTeacher(subject, null, student);
        
        entityManager.getTransaction().begin();

        entityManager.persist(subject);
        entityManager.persist(student);
        entityManager.persist(student.getBelongTo());
        entityManager.persist(teacher);
        entityManager.getTransaction().commit();

        var teacherSubjet = (ArrayList<Teacher>) subjectRepository.findTeachers(subject.getId());
        assertThat(teacherSubjet).isNotEmpty();
        var subjetR = new ArrayList<>(Arrays.asList(teacher));
        assertThat(teacherSubjet).isEqualTo(subjetR);

    

    }
    }


