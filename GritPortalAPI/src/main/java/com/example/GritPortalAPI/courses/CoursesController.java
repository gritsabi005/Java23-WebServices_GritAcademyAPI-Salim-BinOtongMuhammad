package com.example.GritPortalAPI.courses;

import com.example.GritPortalAPI.students.StudentsDTO;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class CoursesController {
    @Autowired
    CoursesService coursesService;

    @GetMapping(value = "/courses", produces = MediaType.APPLICATION_JSON_VALUE)
    List<CoursesDTO> getCourses(){
        return coursesService.getCourses();
    }
    @GetMapping(value = "/courses/findbyid/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CoursesDTO>> coursesById(
            @PathVariable(value = "id") Long id
    ){
        List<CoursesDTO> coursesDTOS = coursesService.coursesById(id);
        return new ResponseEntity<>(coursesDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/courses/findbyname/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CoursesDTO>> coursesByName(
            @PathVariable(value = "name") String name
    ){
        List<CoursesDTO> coursesDTOS = coursesService.coursesByName(name);
        return new ResponseEntity<>(coursesDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/courses/findbyrandomwordinname/{somethingElse}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CoursesDTO>> coursesContaining(
            @PathVariable(value = "somethingElse") String somethingElse
    ){
        List<CoursesDTO> coursesDTOS = coursesService.coursesByRandomWordInName(somethingElse);
        if(coursesDTOS.isEmpty()){
            throw new ResourceNotFoundException("Not Found");
        }
        return new ResponseEntity<>(coursesDTOS, HttpStatus.OK);
    }
    @GetMapping(value = "/courses/findbyrandomwordindescription/{somethingElse}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CoursesDTO>> coursesdescriptionContaining(
            @PathVariable(value = "somethingElse") String somethingElse
    ){
        List<CoursesDTO> coursesDTOS = coursesService.coursesByRandomWordInDescription(somethingElse);
        if(coursesDTOS.isEmpty()){
            throw new ResourceNotFoundException("Not Found");
        }
        return new ResponseEntity<>(coursesDTOS, HttpStatus.OK);
    }
    @PostMapping(value = "/courses/add/{name}/{description}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addCourse(
            @PathVariable("name") String name,
            @PathVariable("description") String description) {
        coursesService.addCourse(name, description);
        return ResponseEntity.ok("course is added");
    }
    @DeleteMapping(value = "/courses/remove/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> removeCourse(
            @PathVariable("id") Long id) {
        coursesService.removeCourseById(id);
        return ResponseEntity.ok("Course " + id + " is removed");
    }

}

