package com.example.GritPortalAPI.students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentsController {
    @Autowired
    StudentsService studentsService;
    @GetMapping(value = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
    List<StudentsDTO> getStudents(){
        return studentsService.getStudents();
    }
    @GetMapping(value = "/students/findbyid/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentsDTO>> studentsById(
            @PathVariable(value = "id") Long id
    ){
        if (id<1) { // to anticipate so that they can not put in negative number
            return new ResponseEntity<>(HttpStatus.GONE);
        } else {
            List<StudentsDTO> students = studentsService.studentsById(id);
            return new ResponseEntity<>(students, HttpStatus.OK);
        }
    }
    @GetMapping(value = "/students/findbyfirstname/{fName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentsDTO>> studentsByFName(
            @PathVariable(value = "fName") String fName
    ){
        List<StudentsDTO> StudentsDTOS = studentsService.studentsByFName(fName);
        if(StudentsDTOS.isEmpty()){
            throw new ResourceNotFoundException("404 Student not found.");
        }
        return new ResponseEntity<>(StudentsDTOS, HttpStatus.OK);
    }
    @GetMapping(value = "/students/findbylastname/{lName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentsDTO>> studentsByLName(
            @PathVariable(value = "lName") String lName
    ){
        List<StudentsDTO> StudentsDTOS = studentsService.studentsByLName(lName);
        return new ResponseEntity<>(StudentsDTOS, HttpStatus.OK);
    }
    @GetMapping(value = "/students/findbytown/{town}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentsDTO>> studentsBytown(
            @PathVariable(value = "town") String town
    ){
        List<StudentsDTO> StudentsDTOS = studentsService.studentsBytown(town);
        return new ResponseEntity<>(StudentsDTOS, HttpStatus.OK);
    }
    @GetMapping(value = "/students/findbyrandomwordinfirstname/{somethingElse}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentsDTO>> studentscontaining(
            @PathVariable(value = "somethingElse") String somethingElse
    ){
        List<StudentsDTO> studentsDTOS = studentsService.getStudentsByRandomWordInFName(somethingElse);
        if(studentsDTOS.isEmpty()){
            throw new ResourceNotFoundException("Not Found");
        }
        return new ResponseEntity<>(studentsDTOS, HttpStatus.OK);
    }
    @GetMapping(value = "/students/findbyrandomwordinlastname/{randomWord}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentsDTO>> studentsByRandomWordInLName(
            @PathVariable(value = "randomWord") String randomWord
    ){
        List<StudentsDTO> studentsDTOS = studentsService.getStudentsByRandomWordInLName(randomWord);
        if(studentsDTOS.isEmpty()){
            throw new ResourceNotFoundException("Not Found");
        }
        return new ResponseEntity<>(studentsDTOS, HttpStatus.OK);
    }
    /*@PostMapping(value = "/students/add/{firstName}/{lastName}/{town}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addStudents(@RequestBody StudentsDTO studentsDTO){
        studentsService.addStudents(studentsDTO.getFName(), studentsDTO.getLName(), studentsDTO.getTown());
        return ResponseEntity.ok("student is added");
    }*/
    @PostMapping(value = "/students/add/{firstName}/{lastName}/{town}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addStudents(
            @PathVariable("firstName") String firstName,
            @PathVariable("lastName") String lastName,
            @PathVariable("town") String town) {
        studentsService.addStudents(firstName, lastName, town);
        return ResponseEntity.ok("student is added");
    }
    @DeleteMapping(value = "/students/remove/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> removeStudents(
            @PathVariable("id") Long id) {
        studentsService.removeStudentById(id);
        return ResponseEntity.ok("Student " + id + " is removed");
    }
    /*
    *
    * public ResponseEntity<List<VehiclesDTO>> getTaxicosForVehicle(
            @PathVariable(value = "id") Integer id
    ){
        List<VehiclesDTO> vehiclesDTO = vehiclesService.getTaxicosForVehicle(id);
        return new ResponseEntity<>(vehiclesDTO, HttpStatus.OK);
    }*/
}
