package com.company.ClinicHibernate;

import com.company.ClinicHibernate.Animals.Cat;
import com.company.ClinicHibernate.Animals.Dog;
import com.company.ConnectHibernate.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

//@SpringBootApplication
public class ClinicApplication {

	public static void main(String[] args) {

		Configuration configuration=new Configuration().configure("hibernate.cfg.xml");
		configuration.addAnnotatedClass(Cat.class);
		configuration.addAnnotatedClass(Dog.class);
		SessionFactory sessionFactory=configuration.buildSessionFactory();

        /*Session session=sessionFactory.getCurrentSession();
        if (session.isOpen()) {
            session=sessionFactory.openSession();
        }*/
		Session session=sessionFactory.openSession();

		Cat cat=new Cat("Tom",20);

		{
			Transaction transaction = session.beginTransaction();

			session.save(cat);

			transaction.commit();
		}

		session.close();
		sessionFactory.close();

		/*var applicationContext=new ClassPathXmlApplicationContext("classpath*:clinic-bean-annotation.xml");

		Clinic clinic=applicationContext.getBean("clinic", Clinic.class);
		System.out.println(clinic.toString());

		applicationContext.close();*/

	}

}
