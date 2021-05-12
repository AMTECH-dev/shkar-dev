package com.company.ConnectHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.*;

public class Connect {
    public static void main(String[] args) {
        Configuration configuration=new Configuration().configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Employee.class);

        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Contact.class);

        configuration.addAnnotatedClass(User2.class);
        configuration.addAnnotatedClass(Contact2.class);

        configuration.addAnnotatedClass(User3.class);
        configuration.addAnnotatedClass(Role.class);

        SessionFactory sessionFactory=configuration.buildSessionFactory();

        /*Session session=sessionFactory.getCurrentSession();
        if (session.isOpen()) {
            session=sessionFactory.openSession();
        }*/
        Session session=sessionFactory.openSession();

        {
            Employee employee = new Employee("Tom", 20, 100000, "SS");

            {
                Transaction transaction = session.beginTransaction();
                session.save(employee);
                transaction.commit();
            }
        }

        {
            User user1 = new User("Tom");
            User user2 = new User("Sam");

            Contact contact1 = new Contact("A", "01.01.2021", user1);
            Contact contact2 = new Contact("B", "01.01.2021", user1);
            Contact contact3 = new Contact("C", "01.01.2021", user2);
            Contact contact4 = new Contact("D", "01.01.2021", user2);
            Contact contact5 = new Contact("E", "01.01.2021", user2);

            {
                Transaction transaction = session.beginTransaction();
                session.save(user1);
                session.save(user2);
                session.save(contact1);
                session.save(contact2);
                session.save(contact3);
                session.save(contact4);
                session.save(contact5);

                transaction.commit();
            }
        }

        {
            Contact2 contact1 = new Contact2("A", "01.01.2021");
            Contact2 contact2 = new Contact2("B", "01.01.2021");
            Contact2 contact3 = new Contact2("C", "01.01.2021");
            Contact2 contact4 = new Contact2("D", "01.01.2021");
            Contact2 contact5 = new Contact2("E", "01.01.2021");

            //List<Contact2> contactsA=Arrays.asList(contact1,contact2,contact3,contact4);
            //List<Contact2> contactsB=Arrays.asList(contact2,contact3,contact5);
            List<Contact2> contactsA=Arrays.asList(contact1,contact2,contact3);
            List<Contact2> contactsB=Arrays.asList(contact4,contact5);

            User2 user21 = new User2("Tom",contactsA);
            User2 user22 = new User2("Sam",contactsB);


            {
                Transaction transaction = session.beginTransaction();
                session.save(contact1);
                session.save(contact2);
                session.save(contact3);
                session.save(contact4);
                session.save(contact5);

                session.save(user21);
                session.save(user22);

                transaction.commit();
            }
        }

        {
            User3 user1 = new User3("Tom");
            User3 user2 = new User3("Sam");

            Role role1=new Role("Role1");
            Role role2=new Role("Role2");
            Role role3=new Role("Role3");
            Role role4=new Role("Role4");
            Role role5=new Role("Role5");

            List<User3> users1=Arrays.asList(user1);
            List<User3> users2=Arrays.asList(user1,user2);
            List<User3> users3=Arrays.asList(user2);

            List<Role> roles1=Arrays.asList(role1,role2,role3);
            List<Role> roles2=Arrays.asList(role1,role2,role3,role4);
            List<Role> roles3=Arrays.asList(role3);
            List<Role> roles4=Arrays.asList(role2,role3,role4);
            List<Role> roles5=Arrays.asList(role4,role5);

            user1.setRoles(roles1);
            user2.setRoles(roles5);

            role1.setUsers(users1);
            role2.setUsers(users2);
            role3.setUsers(users1);
            role4.setUsers(users2);
            role5.setUsers(users1);

            {
                Transaction transaction = session.beginTransaction();
                session.save(role1);
                session.save(role2);
                session.save(role3);
                session.save(role4);
                session.save(role5);

                session.save(user1);
                session.save(user2);

                transaction.commit();
            }
        }

        {
            Transaction transaction = session.beginTransaction();

            int id=1;
            Employee employee1=session.get(Employee.class,id);
            System.out.println(employee1);

            transaction.commit();
        }

        {
            Transaction transaction = session.beginTransaction();

            List<Employee> employees=session.createQuery ("FROM Employee WHERE name LIKE '%T%' AND age BETWEEN 11 AND 14").list();
            for(Employee e : employees) {
                System.out.println(e);
            }

            transaction.commit();
        }

        session.close();
        sessionFactory.close();
    }
}
