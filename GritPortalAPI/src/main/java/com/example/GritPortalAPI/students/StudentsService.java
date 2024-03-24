package com.example.GritPortalAPI.students;

import com.example.GritPortalAPI.courses.Courses;
import com.example.GritPortalAPI.courses.CoursesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentsService {
    @Autowired
    StudentsRepository studentsRepository;

    /*public List<Courses> getCoursesForStudents(Long students_id){
        return studentsRepository.findCourseByStudentsId(students_id);
    }*/

    public List<StudentsDTO> getStudents(){ //why the students red?
        List<StudentsDTO> studentsDTOS = new ArrayList<>();
        studentsRepository.findAll().forEach(students -> studentsDTOS.add(this.mapToDTO(students)));
        return studentsDTOS; //why the find all does not work?
    }
    //to look for random wording you dont need to map it
    public List<StudentsDTO> getStudentsByRandomWordInFName(String somethingElse){
        List<StudentsDTO> studentsDTOS = new ArrayList<>();
        studentsRepository.findByfNameContaining(somethingElse).forEach(students -> studentsDTOS.add(this.mapToDTO(students)));
        return studentsDTOS;
    }
    public List<StudentsDTO> getStudentsByRandomWordInLName(String randomWord) {
        List<StudentsDTO> studentsDTOS = new ArrayList<>();
        studentsRepository.findBylNameContaining(randomWord).forEach(students -> studentsDTOS.add(this.mapToDTO(students)));
        return studentsDTOS;
    }
    // find specific student by id
    public List<StudentsDTO> studentsById(Long id){
        List<StudentsDTO> studentsDTOS = new ArrayList<>();
        studentsRepository.findById(id).map(students
                -> studentsDTOS.add(this.mapToDTO(students)))
                .orElseThrow(() -> new ResourceNotFoundException("404 User not found with id " + id));
        return studentsDTOS;
    }
    public List<StudentsDTO> studentsByFName(String fname){
        List<Students> studentsbyfname = studentsRepository.findByfName(fname);
        return studentsbyfname.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    public List<StudentsDTO> studentsByLName(String lname){
        List<Students> studentsbylname = studentsRepository.findBylName(lname);
        return studentsbylname.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    public List<StudentsDTO> studentsBytown(String town){
        List<Students> studentsbytown = studentsRepository.findBytown(town);
        return studentsbytown.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    public void addStudents(String firstName, String lastName, String theirTowns) {
        Students morestudent = new Students();
        morestudent.setFName(firstName);
        morestudent.setLName(lastName);
        morestudent.setTown(theirTowns);
        studentsRepository.save(morestudent);
    }
    public void removeStudentById(Long id){
        Optional<Students> studentsOptional = studentsRepository.findById(id);
        if (studentsOptional.isPresent()){
            Students students = studentsOptional.get();
            studentsRepository.delete(students);
        } else{
            throw new ResourceNotFoundException("Student with id " + id + " not found.");
        }
    }
    private StudentsDTO mapToDTO(Students students) {
        StudentsDTO dto = new StudentsDTO();
        dto.setId(students.getId());
        dto.setFName(students.getFName());
        dto.setLName(students.getLName());
        dto.setTown(students.getTown());
        dto.setCoursesDTOS(students.getCourses().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList()));
        return dto;
    }
    private CoursesDTO mapToDTO(Courses courses){
        CoursesDTO dto = new CoursesDTO();
        dto.setId(courses.getId());
        dto.setName(courses.getName());
        dto.setDescription(courses.getDescription());
        return dto;
    }
}
