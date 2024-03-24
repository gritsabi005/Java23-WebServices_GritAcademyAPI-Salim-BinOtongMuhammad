package com.example.GritPortalAPI.courses;

import com.example.GritPortalAPI.ResourceNotFound;
import com.example.GritPortalAPI.students.Students;
import com.example.GritPortalAPI.students.StudentsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CoursesService {
    @Autowired
    CoursesRepository coursesRepository;

    public List<CoursesDTO> getCourses(){
        List<CoursesDTO> coursesDTOS  = new ArrayList<>();
        coursesRepository.findAll().forEach(courses -> coursesDTOS.add(this.mapToDTO(courses)));
        return coursesDTOS;
    }

    public List<CoursesDTO> coursesById(Long id){
        Optional<Courses> courses = coursesRepository.findById(id).map(coursesid ->{
            coursesid.getStudents().size();
            return coursesid;
        });
        return courses.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // to look for the random word in the name
    public List<CoursesDTO> coursesByRandomWordInName(String somethingElse){
        List<CoursesDTO> coursesDTOS = new ArrayList<>();
        coursesRepository.findBynameContaining(somethingElse).forEach(courses -> coursesDTOS.add(this.mapToDTO(courses)));
        return coursesDTOS;
    }

    // to look for the random word in the description
    public List<CoursesDTO> coursesByRandomWordInDescription(String somethingElse){
        List<CoursesDTO> coursesDTOS = new ArrayList<>();
        coursesRepository.findBydescriptionContaining(somethingElse).forEach(courses -> coursesDTOS.add(this.mapToDTO(courses)));
        return coursesDTOS;
    }
    public List<CoursesDTO> coursesByName(String name){
        List<Courses> coursesByName = coursesRepository.findByName(name);
        return coursesByName.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public void addCourse(String name, String description) {
        Courses newCourse = new Courses();
        newCourse.setName(name);
        newCourse.setDescription(description);
        coursesRepository.save(newCourse);
    }

    public void removeCourseById(Long id){
        Optional<Courses> coursesOptional = coursesRepository.findById(id);
        if (coursesOptional.isPresent()){
            Courses courses = coursesOptional.get();
            coursesRepository.delete(courses);
        } else{
            throw new ResourceNotFoundException("Course with id " + id + " not found.");
        }
    }


    private CoursesDTO mapToDTO(Courses courses){
        CoursesDTO dto = new CoursesDTO();
        dto.setId(courses.getId());
        dto.setName(courses.getName());
        dto.setDescription(courses.getDescription());
        dto.setStudentsDTOS(courses.getStudents().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList()));
        return dto;
    }

    private StudentsDTO mapToDTO(Students students){
        StudentsDTO dto = new StudentsDTO();
        dto.setId(students.getId());
        dto.setFName(students.getFName());
        dto.setLName(students.getLName());
        dto.setTown(students.getTown());
        return dto;
    }
}
