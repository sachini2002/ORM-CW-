package lk.ijse.dto;
import lk.ijse.entity.Courses;
import lk.ijse.entity.Payment;
import lk.ijse.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegistrationDTO {

    private String registrationId;
    private double advanced;
    private LocalDate date;
    private Courses courses;
    private Student student;
    private Payment payment;
}
