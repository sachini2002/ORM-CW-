package lk.ijse.dao;

import lk.ijse.entity.Courses;

import java.util.List;


public interface CourseDao extends CrudDao<Courses>{

    List<Courses> loadTable();
}
