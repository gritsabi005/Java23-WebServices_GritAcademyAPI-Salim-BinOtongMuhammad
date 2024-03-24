package com.example.GritPortalAPI.Students_courses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Students_coursesService {
    @Autowired
    Students_coursesRepository studentsCoursesRepository;

    public List<Students_coursesDTO> getStudents_courses(){
        List<Students_coursesDTO> studentsCoursesDTOS = new ArrayList<>();
        studentsCoursesRepository.findAll().forEach(studentsCourses -> studentsCoursesDTOS.add(this.mapToDTO(studentsCourses)));
        return studentsCoursesDTOS;
    }

    private Students_coursesDTO mapToDTO(Students_courses studentsCourses){
        Students_coursesDTO dto = new Students_coursesDTO();
        dto.setId(studentsCourses.getId());
        dto.setStudents_id(studentsCourses.getStudents().getId());
        dto.setCourses_id(studentsCourses.getCourses().getId());
        return dto;
    }

}

