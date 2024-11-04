package lk.ijse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import lombok.Setter;

import java.util.List;

@Entity
public class Courses {

    @Id
    private String courseId;
    private String courseName;
    @Setter
    private String duration;
    private double coursePrice;

    @OneToMany(mappedBy = "courses", cascade = CascadeType.ALL) // Cascade all operations
    private List<Registration> registrations;

    public Courses() {

    }
    public Courses(String courseId, String courseName, String duration, double coursePrice) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.duration = duration;
        this.coursePrice = coursePrice;

    }
    public String getCourseId() {
        return courseId;
    }
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getCoursePrice() {
        return coursePrice;
    }
    public void setCoursePrice(double coursePrice) {
        this.coursePrice = coursePrice;
    }

    @Override
    public String toString() {
        return courseId;
    }


}
