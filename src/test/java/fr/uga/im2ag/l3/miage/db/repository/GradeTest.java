package fr.uga.im2ag.l3.miage.db.repository;

import fr.uga.im2ag.l3.miage.db.repository.api.GradeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
class GradeTest extends Base {

    GradeRepository gradeRepository;

    @BeforeEach
    void before() {
        gradeRepository = daoFactory.newGradeRepository(entityManager);
    }

    @AfterEach
    void after() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
    }

    @Test
    void shouldSaveGrade() {

        final var grade = Fixtures.createGrade(Fixtures.createSubject());
        entityManager.getTransaction().begin();
        entityManager.persist(grade.getSubject());
        gradeRepository.save(grade);
        entityManager.getTransaction().commit();
        entityManager.detach(grade);
        var pGrade = gradeRepository.findById(grade.getId());
        assertThat(pGrade).isNotNull().isNotSameAs(grade).isEqualTo(grade);
    }
    

    @Test
    void shouldFailUpgradeGrade() {

        final var subject = Fixtures.createSubject();
        final var grade = Fixtures.createGrade(subject);
        grade.setValue(10F);
        entityManager.getTransaction().begin();
        entityManager.persist(subject);
        gradeRepository.save(grade);
        entityManager.getTransaction().commit();
        grade.setValue(5F);
        gradeRepository.save(grade); 
        entityManager.detach(grade);
        var graDe = gradeRepository.findById(grade.getId());
        assertThat(graDe.getValue()).isNotEqualTo(grade.getValue());

    }

    

    @Test
    void shouldFindHighestGrades() {
        // TODO
    }

    @Test
    void shouldFindHighestGradesBySubject() {
        // TODO
    }

}
