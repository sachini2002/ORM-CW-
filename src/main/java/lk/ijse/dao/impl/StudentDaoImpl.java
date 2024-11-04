package lk.ijse.dao.impl;

import lk.ijse.config.SessionFactoryConfuguration;
import lk.ijse.dao.StudentDao;
import lk.ijse.entity.Student;
import lk.ijse.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    @Override
    public boolean save(Student student) {
         Session session = SessionFactoryConfuguration.getSessionFactoryConfuguration().getSession();
         Transaction tx = session.beginTransaction();
         session.save(student); // Save the student
         tx.commit(); // Commit the transaction
         System.out.println("Student saved successfully!");
         return true;
    }

    @Override
    public boolean update(Student student) {
        try {
            Session session = SessionFactoryConfuguration.getSessionFactoryConfuguration().getSession();
            Transaction tx = session.beginTransaction();
            session.update(student);
            tx.commit();
            System.out.println("Student updated successfully!");
        }catch (HibernateException e) {
            System.out.println("Failed to save student due to a database error.");
        }
        return true;

    }

    @Override
    public boolean delete(int student) {

        Session session = SessionFactoryConfuguration.getSessionFactoryConfuguration().getSession();
        Transaction tx = session.beginTransaction();
        session.delete(session.get(Student.class, student));
        tx.commit();
        System.out.println("Student deleted successfully!");
        return true;
    }

    @Override
    public boolean delete(String t) {
        return false;
    }

    @Override
    public Student find(String id) {
            Session session = SessionFactoryConfuguration.getSessionFactoryConfuguration().getSession();
            Transaction tx = session.beginTransaction();
            Student student = (Student) session.get(Student.class, id);
            tx.commit();
            return student;
    }

    @Override
    public List<User> getid() {
        List<User> users = new ArrayList<>();

        try (Session session = SessionFactoryConfuguration.getSessionFactoryConfuguration().getSession()) {
            // Use HQL to get all User objects
            String hql = "FROM User"; // This will retrieve all User entities
            Query<User> query = session.createQuery(hql, User.class);
            users = query.list(); // This will now contain User objects
        } catch (Exception e) {
            e.printStackTrace(); // Consider using a logger instead of printStackTrace
        }

        return users;
    }

    @Override
    public List<Student> loadTable() {
        Session session = SessionFactoryConfuguration.getSessionFactoryConfuguration().getSession();
        Transaction transaction = session.beginTransaction();

         List<Student> list = session.createQuery("from Student").list();
         transaction.commit();
         session.close();
         for (Student student : list) {
             System.out.println(student.getStudentId());
         }
         return list;

    }

}
