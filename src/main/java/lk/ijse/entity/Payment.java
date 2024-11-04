package lk.ijse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Payment {

    @Id
    private String paymentId;
    private double amount;
    private double paidAmount;
    private double balance;

    public Payment(String paymentId, double amount) {
        this.paymentId = paymentId;
        this.amount = amount;
    }
    @Override
    public String toString() {
        return " " +paymentId ;
    }

}
