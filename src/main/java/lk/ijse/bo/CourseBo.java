package lk.ijse.bo;

import lk.ijse.dto.CoursesDTO;

import java.util.List;


public interface CourseBo extends SuperBo{
    boolean saveCourses(CoursesDTO coursesDTO);


    CoursesDTO searchCourse(String courseId);

    boolean deleteCourse(String courseIdText);

    boolean updateCourse(CoursesDTO coursesDTO);

    List<CoursesDTO> loadTable();
}
