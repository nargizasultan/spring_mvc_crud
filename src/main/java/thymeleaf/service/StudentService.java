package thymeleaf.service;

import org.springframework.stereotype.Service;
import thymeleaf.model.Course;
import thymeleaf.model.Student;
import thymeleaf.repository.CourseRepository;
import thymeleaf.repository.StudentRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * @author Beksultan
 */
@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;


    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;

        this.courseRepository = courseRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public List<Student> findByCourseId(UUID courseId) {
        return studentRepository.findByCourseId(courseId);
    }

    @Transactional
    public void save(UUID courseId, Student student) {
        Course course = courseRepository.findById(courseId);
        student.setCourse(course);
        course.setStudent(student);
        studentRepository.save(student);

    }
}
