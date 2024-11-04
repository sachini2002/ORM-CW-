package lk.ijse.bo.impl;

import lk.ijse.bo.PaymentBo;
import lk.ijse.dao.DaoFactory;
import lk.ijse.dao.DaoType;
import lk.ijse.dao.PaymentDao;
import lk.ijse.dto.PaymentDTO;
import lk.ijse.entity.Payment;

import java.util.ArrayList;
import java.util.List;

public class PaymentBoImpl implements PaymentBo {

    PaymentDao paymentDao = (PaymentDao) DaoFactory.getInstance().getDao(DaoType.Payment);
    @Override
    public boolean savePayment(PaymentDTO paymentDTO) {
       return paymentDao.save(new Payment(paymentDTO.getPaymentId(), paymentDTO.getAmount()));
    }

    @Override
    public boolean removePayment(String payId) {
       return paymentDao.delete(payId);
    }

    @Override
    public List<PaymentDTO> loadTable() {
        List<Payment> payments = paymentDao.loadTable();
        List<PaymentDTO> paymentDTOS = new ArrayList<>();
        for (Payment payment : payments) {
            PaymentDTO paymentDTO = new PaymentDTO(payment.getPaymentId(), payment.getAmount(),payment.getBalance(),payment.getPaidAmount());
            paymentDTOS.add(paymentDTO);
        }
        return paymentDTOS;
    }
}
