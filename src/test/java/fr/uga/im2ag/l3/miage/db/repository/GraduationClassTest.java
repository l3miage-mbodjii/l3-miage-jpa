package fr.uga.im2ag.l3.miage.db.repository;

import fr.uga.im2ag.l3.miage.db.repository.api.GraduationClassRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
class GraduationClassTest extends Base {

    GraduationClassRepository classRepository;

    @BeforeEach
    void before() {
        classRepository = daoFactory.newGraduationClassRepository(entityManager);
    }

    @AfterEach
    void after() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
    }

    @Test
    void shouldSaveClass() {

        final var cla = Fixtures.createClass();
      
        entityManager.getTransaction().begin();
        classRepository.save(cla);
        entityManager.getTransaction().commit();
        entityManager.detach(cla);
        var cLass = classRepository.findById(cla.getId());
        assertThat(cLass).isNotNull().isNotSameAs(cla).isEqualTo(cla);

       
    }


    @Test
    void shouldFindByYearAndName() {
        
        final var clasS = Fixtures.createClass();
        clasS.setName("maman");
        clasS.setYear(1999);

        entityManager.getTransaction().begin();
        classRepository.save(clasS);
        entityManager.getTransaction().commit();
        entityManager.detach(clasS);

        var pClassBDSI2022 = classRepository.findByYearAndName(2022, "maman");
        assertThat(pClassBDSI2022).isNotNull().isNotSameAs(clasS).isEqualTo(clasS);
    }

}
