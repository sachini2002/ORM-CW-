package lk.ijse.dto;

public class CoursesDTO {

    private String courseId;
    private String courseName;
    private String duration;
    private double coursePrice;

    // No-argument constructor
    public CoursesDTO() {
    }

    // All-argument constructor
    public CoursesDTO(String courseId, String courseName, String duration, double coursePrice) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.duration = duration;
        this.coursePrice = coursePrice;
    }

    // Getters and Setters
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
        return "Courses{" +
                "courseId='" + courseId + '\'' +
                '}';
    }


}
