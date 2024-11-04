package lk.ijse.bo.impl;

import lk.ijse.bo.CourseBo;
import lk.ijse.dao.CourseDao;
import lk.ijse.dao.DaoFactory;
import lk.ijse.dao.DaoType;
import lk.ijse.dto.CoursesDTO;
import lk.ijse.entity.Courses;

import java.util.ArrayList;
import java.util.List;


public class CourseBoImpl implements CourseBo {

    CourseDao courseDao = (CourseDao) DaoFactory.getInstance().getDao(DaoType.Course);
    @Override
    public boolean saveCourses(CoursesDTO coursesDTO) {
        boolean save = courseDao.save(new Courses(coursesDTO.getCourseId(), coursesDTO.getCourseName(), coursesDTO.getDuration(), coursesDTO.getCoursePrice()));
        return save;
    }

    @Override
    public CoursesDTO searchCourse(String courseId) {
        Courses courses = courseDao.find(courseId);
        System.out.println(courses.getCourseId());
        System.out.println(courses.getCourseName());
        System.out.println(courses.getDuration());
        System.out.println(courses.getCoursePrice());
        CoursesDTO coursesDTO = new CoursesDTO(courses.getCourseId(), courses.getCourseName(), courses.getDuration(), courses.getCoursePrice());
        return coursesDTO;
    }

    @Override
    public boolean deleteCourse(String courseIdText) {
        boolean deletee = courseDao.delete(courseIdText);
        return deletee;
    }

    @Override
    public boolean updateCourse(CoursesDTO coursesDTO) {
      return   courseDao.update(new Courses(coursesDTO.getCourseId(),coursesDTO.getCourseName(),coursesDTO.getDuration(),coursesDTO.getCoursePrice()));
    }

    @Override
    public List<CoursesDTO> loadTable() {
        List<Courses> list = courseDao.loadTable();
        List<CoursesDTO> coursesDTOList = new ArrayList<>();
        for (Courses courses : list) {
            CoursesDTO coursesDTO = new CoursesDTO(courses.getCourseId(),courses.getCourseName(),courses.getDuration(),courses.getCoursePrice());
            coursesDTOList.add(coursesDTO);
        }
        return coursesDTOList;
    }


}
