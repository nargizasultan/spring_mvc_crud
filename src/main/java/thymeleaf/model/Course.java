

package thymeleaf.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "courses")
@Getter
@Setter
@ToString
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private String address;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students = new ArrayList<>();

    public void setStudent(Student student) {
        this.students.add(student);
    }
}
