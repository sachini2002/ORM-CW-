package lk.ijse.config;


import lk.ijse.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryConfuguration {

    private static SessionFactoryConfuguration sessionFactoryConfuguration;

    private static SessionFactory sessionFactory;

    public SessionFactoryConfuguration() {
        Configuration configure = new Configuration().configure().addAnnotatedClass(User.class).addAnnotatedClass(Student.class).addAnnotatedClass(Registration.class).addAnnotatedClass(Courses.class).addAnnotatedClass(Payment.class);
        sessionFactory = configure.buildSessionFactory();
    }

    public static SessionFactoryConfuguration getSessionFactoryConfuguration(){
        if(sessionFactoryConfuguration == null){
            sessionFactoryConfuguration = new SessionFactoryConfuguration();
        }
        return sessionFactoryConfuguration;
    }
    public Session getSession(){
        return sessionFactory.openSession();
    }

}
