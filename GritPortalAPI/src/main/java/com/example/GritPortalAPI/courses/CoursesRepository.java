package com.example.GritPortalAPI.courses;

import com.example.GritPortalAPI.students.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CoursesRepository extends JpaRepository<Courses, Long> {

    List<Courses> findByName(String name);

    //search by specific word in the name
    List<Courses> findBynameContaining(String somethingElse);

    List<Courses> findBydescriptionContaining(String somethingElse);
}
