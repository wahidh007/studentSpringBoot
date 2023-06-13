package net.isett.studentsspring.students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @PostMapping
    public void newStudent(@RequestBody Student s) {
        studentService.addStudent(s);
    }

    @GetMapping("/student")
    public ModelAndView oneStudent(@RequestParam(required = false) Long id) {
        Optional<Student> s = studentService.getOneStudent(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student");
        modelAndView.addObject("student", s);
        return modelAndView;
    }
    @GetMapping
    private List<Student> getStudents() {
        return studentService.getStudents();
    }
    @GetMapping(path="{id}")
    private Optional<Student> getStudentById(@PathVariable("id") Long id) {
        return studentService.getOneStudent(id);
    }

    @GetMapping(path="email/{email}")
    public Optional<Student> getStudentByEmail(@PathVariable("email") String email) {
        return studentService.getStudentByEmail(email);
    }
    @DeleteMapping(path = "{studentID}")
    private Optional<String> deleteStudent(@PathVariable("studentID") Long id) {
        studentService.deleteStudent(id);

        return Optional.of("Student deleted !");
    }

//    @PutMapping(path = "{studentID}")
//    private void updateStudent(@PathVariable("studentID") Long id,
//                               @RequestParam(required = false) String name,
//                               @RequestParam(required = false) LocalDate birthDate,
//                               @RequestParam(required = false) String email) {
////        studentService.updateStudent(id, name, birthDate, email);
//        System.out.println(id + " " + name + " " + birthDate + " " + email);
//    }

    @PutMapping(path = "{studentID}")
    private void updateStudent(@PathVariable("studentID") Long id, @RequestBody Student s) {
        studentService.updateStudent(id, s);
        System.out.println(id + " " + s);
    }
}
