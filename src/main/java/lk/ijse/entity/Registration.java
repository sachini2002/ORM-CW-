package lk.ijse.entity;

import jakarta.persistence.*;
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
@Entity
public class Registration {
    @Id
    private String registrationId;

    private double advanced;

    private LocalDate date;

    @ManyToOne
    private Courses courses;

    @ManyToOne(cascade = CascadeType.ALL) // Cascade all operations
    private Student student;

    @OneToOne(cascade = CascadeType.ALL)
    private Payment payment;
}
