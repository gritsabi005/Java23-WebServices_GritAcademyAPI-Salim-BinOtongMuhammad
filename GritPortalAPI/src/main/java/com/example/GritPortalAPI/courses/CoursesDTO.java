package com.example.GritPortalAPI.courses;

import com.example.GritPortalAPI.students.StudentsDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CoursesDTO {

    private Long id;
    private String name;
    private String description;

    List<StudentsDTO> studentsDTOS = new ArrayList<>();
}