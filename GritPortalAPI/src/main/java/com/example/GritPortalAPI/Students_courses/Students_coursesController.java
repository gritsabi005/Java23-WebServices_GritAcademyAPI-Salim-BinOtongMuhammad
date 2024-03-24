package com.example.GritPortalAPI.Students_courses;

import com.example.GritPortalAPI.courses.CoursesService;
import com.example.GritPortalAPI.students.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Students_coursesController {
    @Autowired
    private Students_coursesService studentsCoursesService;
    @Autowired
    private StudentsService studentsService;
    @Autowired
    private CoursesService coursesService;

    @GetMapping(value = "/students-courses", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Students_coursesDTO>> getAllStudentCourses() {
        List<Students_coursesDTO> studentCoursesList = studentsCoursesService.getStudents_courses();
        return ResponseEntity.ok(studentCoursesList);
    }
}
