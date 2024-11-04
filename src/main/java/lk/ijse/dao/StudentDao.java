package lk.ijse.dao;

import lk.ijse.entity.Student;
import lk.ijse.entity.User;

import java.util.List;

public interface StudentDao extends CrudDao<Student>{
    List<User> getid();

    List<Student> loadTable();
}
