package lk.ijse.dao;

import lk.ijse.entity.Courses;
import lk.ijse.entity.Payment;
import lk.ijse.entity.Registration;
import lk.ijse.entity.Student;

import java.util.List;

public interface RegistrationDao extends CrudDao<Registration>{
    List<Courses> getIds();

    List<Student> getStudentIds();


    List<Registration> loadTable();

    List<Payment> getpayId();


    public Registration findRegistrationById(String regId) ;
}
