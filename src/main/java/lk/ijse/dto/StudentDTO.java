package lk.ijse.dto;
import lk.ijse.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentDTO {

    private int StudentId;
    private String StudentName;
    private String StudentAddress;
    private String StudentPhone;
    private String StudentEmail;
    private User userid;
}
