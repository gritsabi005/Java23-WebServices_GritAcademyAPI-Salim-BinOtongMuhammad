package com.example.GritPortalAPI.students;

import com.example.GritPortalAPI.courses.CoursesDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentsDTO {



    private Long id;

    private String fName;

    private String lName;

    private String town;

    List<CoursesDTO> coursesDTOS = new ArrayList<>();



}
