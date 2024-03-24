package com.example.GritPortalAPI.Students_courses;
import com.example.GritPortalAPI.courses.Courses;
import com.example.GritPortalAPI.students.Students;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "students_courses")
@Table(name = "students_courses")

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Students_courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="students_id",
    referencedColumnName = "id")
    private Students students;

    @ManyToOne
    @JoinColumn(name = "courses_id",
    referencedColumnName = "Id")
    private Courses courses;
}
