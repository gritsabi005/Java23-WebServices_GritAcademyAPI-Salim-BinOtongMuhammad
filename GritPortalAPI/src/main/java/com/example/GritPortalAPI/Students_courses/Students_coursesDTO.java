package com.example.GritPortalAPI.Students_courses;

import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Students_coursesDTO {

    private Long id;

    private Long students_id;

    private Long courses_id;
}

/*
*
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
* */