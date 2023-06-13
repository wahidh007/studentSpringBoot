package net.isett.studentsspring.students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public void addStudent(Student s) {
        studentRepository.save(s);
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long id, Student pStudent) {
        Student s = studentRepository.findById(id).orElseThrow(() ->
            new IllegalStateException("Student doesn't exist !")
        );

        s.setName(pStudent.getName());
        s.setBirthDate(pStudent.getBirthDate());
        s.setEmail(pStudent.getEmail());
        studentRepository.save(s);
    }

    public Optional<Student> getOneStudent(Long id) {
        Student s = studentRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("Student doesn't exist !")
        );

        return Optional.ofNullable(s);
    }

    public Optional<Student> getStudentByEmail(String email) {
        return studentRepository.findStudentByEmail(email);
    }
}
