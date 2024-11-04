package lk.ijse.bo.impl;

import javafx.scene.control.Alert;
import lk.ijse.bo.StudentBo;
import lk.ijse.dao.DaoFactory;
import lk.ijse.dao.DaoType;
import lk.ijse.dao.StudentDao;
import lk.ijse.dto.StudentDTO;
import lk.ijse.entity.Student;
import lk.ijse.entity.User;

import java.util.ArrayList;
import java.util.List;

public class StudentBoImpl implements StudentBo {


    StudentDao studentDao = (StudentDao) DaoFactory.getInstance().getDao(DaoType.Student);
    @Override
    public boolean addStudent(StudentDTO student) {

     return  studentDao.save(new Student(student.getStudentId(),student.getStudentName(),student.getStudentAddress(),student.getStudentPhone(),student.getStudentEmail(),student.getUserid()));

    }

    @Override
    public List<User> getUserIds() {
       return studentDao.getid();
    }

    @Override
    public boolean updateStudent(StudentDTO student) {
     return studentDao.update(new Student(student.getStudentId(),student.getStudentName(),student.getStudentAddress(),student.getStudentPhone(),student.getStudentEmail(),student.getUserid()));
    }

    @Override
    public StudentDTO searchStudent(int studentIdText) {
        Student student1 = studentDao.find(String.valueOf(studentIdText));


        StudentDTO studentDTO = new StudentDTO(student1.getStudentId(), student1.getStudentName(), student1.getStudentAddress(), student1.getStudentPhone(), student1.getStudentEmail(), student1.getUserid());
        return studentDTO;


    }

    @Override
    public boolean deleteStudent(int studentIdText) {
        return studentDao.delete(studentIdText);
    }

    @Override
    public List<StudentDTO> loadTable() {
        List<Student> students = studentDao.loadTable();
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student : students) {
            StudentDTO studentDTO = new StudentDTO(student.getStudentId(), student.getStudentName(), student.getStudentAddress(), student.getStudentPhone(), student.getStudentEmail(), student.getUserid());
            studentDTOS.add(studentDTO);
        }
        return studentDTOS;

    }
}
