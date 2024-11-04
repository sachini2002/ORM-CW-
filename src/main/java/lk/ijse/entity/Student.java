package lk.ijse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import java.util.List;

@Entity
public class Student {
    @Id
    private int StudentId;
    private String StudentName;
    private String StudentAddress;
    private String StudentPhone;
    private String StudentEmail;

    @ManyToOne
    private User userid;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL) // Cascade all operations
    private List<Registration> registrations;

    // No-argument constructor
    public Student() {
    }

    // Constructor with parameters
    public Student(int studentId, String studentName, String studentAddress, String studentPhone, String studentEmail, User userid) {
        this.StudentId = studentId;
        this.StudentName = studentName;
        this.StudentAddress = studentAddress;
        this.StudentPhone = studentPhone;
        this.StudentEmail = studentEmail;
        this.userid = userid;
    }



    // Getters and Setters
    public int getStudentId() {
        return StudentId;
    }

    public void setStudentId(int studentId) {
        this.StudentId = studentId;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        this.StudentName = studentName;
    }

    public String getStudentAddress() {
        return StudentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.StudentAddress = studentAddress;
    }

    public String getStudentPhone() {
        return StudentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.StudentPhone = studentPhone;
    }

    public String getStudentEmail() {
        return StudentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.StudentEmail = studentEmail;
    }

    public User getUserid() {
        return userid;
    }

    public void setUserid(User userid) {
        this.userid = userid;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }

    @Override
    public String toString() {
        return  ""+ StudentId;
    }




}
