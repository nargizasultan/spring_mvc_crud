package thymeleaf.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Beksultan
 */
@Entity
@Table(name = "students")
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private String email;

    @ManyToMany
    private List<Course> courses = new ArrayList<>();

    public void setCourse(Course course) {
        this.courses.add(course);
    }
}