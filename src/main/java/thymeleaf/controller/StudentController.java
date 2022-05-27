package thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import thymeleaf.model.Student;
import thymeleaf.service.StudentService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @ModelAttribute("studentList")
    public List<Student> findAllStudents() {
        return studentService.findAll();
    }

    @GetMapping("find/by/{courseId}")
    public String findAllStudentsByCourseId(@PathVariable UUID courseId, Model model) {

        List<Student> students = studentService.findByCourseId(courseId);
        model.addAttribute("students", students);
        model.addAttribute("courseId", courseId);
        return "students";
    }

    @GetMapping("/save/{courseId}")
    public String showStudentSavePage(@PathVariable UUID courseId, Model model) {
        model.addAttribute("courseID", courseId);
        model.addAttribute("emptyStudent", new Student());
        return "studentSavePage";


    }
    @PostMapping("/save/{courseId}")
    public String saveStudent(Student student, @PathVariable UUID courseId){
        studentService.save(courseId, student);
        return "redirect:/api/students/find/by/"+courseId;

    }

}
