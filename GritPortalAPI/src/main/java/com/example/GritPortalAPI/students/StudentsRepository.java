package com.example.GritPortalAPI.students;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.GritPortalAPI.courses.Courses;

import java.util.List;
@Repository
public interface StudentsRepository extends JpaRepository<Students, Long> {
    List<Students> findByfName(String fname);
    List<Students> findBylName(String lname);
    List<Students> findBytown(String town);

    /*@Query("SELECT su")tend
    List<Students> gimmefName(String fname);*/

    List<Students> findByfNameContaining(String somethingElse);

    List<Students> findBylNameContaining(String randomWord);

    /*List<Course> findCourseByStudentsId(Long students_id);*/


}
