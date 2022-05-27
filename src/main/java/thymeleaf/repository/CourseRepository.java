package thymeleaf.repository;

import org.springframework.stereotype.Repository;
import thymeleaf.model.Course;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * @author Beksultan
 */
@Repository
public class CourseRepository {

    private final EntityManager entityManager;

    public CourseRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public void save(Course course) {
        entityManager.getTransaction().begin();
        entityManager.persist(course);
        entityManager.getTransaction().commit();
    }

    public Course findById(UUID courseId) {
        return entityManager.find(Course.class, courseId);
    }

    public List<Course> findAll() {
        return entityManager
                .createQuery("select c from Course c", Course.class)
                .getResultList();
    }

    public void deleteById(UUID courseId) {
        entityManager.remove(entityManager.find(Course.class, courseId));
    }
}
