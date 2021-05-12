package com.company.ConnectHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Connect {
    public static void main(String[] args) {
        Configuration configuration=new Configuration().configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Employee.class);
        SessionFactory sessionFactory=configuration.buildSessionFactory();

        Session session=sessionFactory.getCurrentSession();

        Employee employee=new Employee("Tom",20,100000,"SS");
        session.save(employee);

        Transaction transaction=session.beginTransaction();

        transaction.commit();

        session.close();
        sessionFactory.close();
    }
}
