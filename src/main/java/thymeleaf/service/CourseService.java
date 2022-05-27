package thymeleaf.service;

import org.springframework.stereotype.Service;
import thymeleaf.model.Course;
import thymeleaf.repository.CourseRepository;

import java.util.List;

/**
 * @author Beksultan
 */
@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }


    public void save(Course course) {
        courseRepository.save(course);
    }
}