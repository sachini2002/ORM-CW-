package lk.ijse.dao.impl;

import lk.ijse.config.SessionFactoryConfuguration;
import lk.ijse.dao.PaymentDao;
import lk.ijse.entity.Payment;
import lk.ijse.entity.Registration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PaymentDaoImpl implements PaymentDao {
    @Override
    public boolean save(Payment payment) {
        try {
            Session session = SessionFactoryConfuguration.getSessionFactoryConfuguration().getSession();
            Transaction tx = session.beginTransaction();
            session.save(payment);
            tx.commit();
            session.close();
            return true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public boolean update(Payment payment) {
        return false;
    }

    @Override
    public boolean delete(int t) {
        return false;
    }

    @Override
    public boolean delete(String t) {
        try {
            Session session = SessionFactoryConfuguration.getSessionFactoryConfuguration().getSession();
            Transaction tx = session.beginTransaction();
            session.delete(session.get(Payment.class, t));
            tx.commit();
            session.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Payment find(String id) {
        return null;
    }

    @Override
    public List<Payment> loadTable() {
        Session session = SessionFactoryConfuguration.getSessionFactoryConfuguration().getSession();
        Transaction tx = session.beginTransaction();
        List <Payment> list  = session.createQuery("from Payment").list();
        tx.commit();
        session.close();
        return list;
    }
}
