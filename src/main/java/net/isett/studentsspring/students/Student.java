package net.isett.studentsspring.students;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter @Setter @NoArgsConstructor @ToString
@Table(name = "studentsmy2")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_STUDENT")
    @SequenceGenerator(name = "SEQUENCE_STUDENT", sequenceName = "studentsmy2_id_seq", allocationSize = 1)
    private Long id;
    private String name;
    @Column(name = "datenais")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;
    private String email;

    public Student(Long id, String name, LocalDate birthDate, String email) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.email = email;
    }
}
