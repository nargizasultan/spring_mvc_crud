package thymeleaf.repository;

import org.springframework.stereotype.Repository;
import thymeleaf.model.Course;
import thymeleaf.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.UUID;

/**
 * @author Beksultan
 */
@Repository
public class StudentRepository {

    private final EntityManager entityManager;

    public StudentRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    // save
    public void save(Student student) {
        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.getTransaction().commit();
    }

    // find by id
    public Student findById(UUID studentId) {
        return entityManager.find(Student.class, studentId);
    }

    // find all
    public List<Student> findAll() {
        return entityManager.createQuery("select s from Student s", Student.class)
                .getResultList();
    }

    // remove
    public void removeById(UUID studentId) {
        entityManager.createQuery("delete from Student s where s.id = ?1")
                .setParameter(1, studentId)
                .executeUpdate();
    }

    public List<Student> findByCourseId(UUID courseId) {
        return entityManager
                .createQuery("select s from Student s where (select c from Course c where c.id = ?1) member of s.courses", Student.class)
                .setParameter(1, courseId)
                .getResultList();
    }
}