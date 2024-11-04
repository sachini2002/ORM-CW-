package lk.ijse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class User {



    @Id
    private String username;
    private String password;
    private String role;

    @OneToMany(mappedBy = "userid", cascade = CascadeType.ALL) // Cascade all operations
    private List<Student> students;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }


    @Override
    public String toString() {
        return this.username; // Change to whatever field you want to display
    }
}
