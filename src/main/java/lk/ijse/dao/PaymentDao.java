package lk.ijse.dao;

import lk.ijse.entity.Payment;

import java.util.List;

public interface PaymentDao extends CrudDao<Payment>{
    List<Payment> loadTable();
}
