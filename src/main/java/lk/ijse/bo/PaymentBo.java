package lk.ijse.bo;

import lk.ijse.dto.PaymentDTO;

import java.util.List;

public interface PaymentBo extends SuperBo{
    boolean savePayment(PaymentDTO paymentDTO);

    boolean removePayment(String payId);

    List<PaymentDTO> loadTable();
}
