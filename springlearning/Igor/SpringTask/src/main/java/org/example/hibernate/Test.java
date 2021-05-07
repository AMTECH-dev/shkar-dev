package org.example.hibernate;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
  private final SessionFactory factory;

  @Autowired
  public Test(SessionFactory factory) {
    this.factory = factory;
  }

  public SessionFactory getFactory() {
    return factory;
  }

  public List<TestOwner> getOwners() {
    final Session session = factory.openSession();
    final Transaction transaction = session.beginTransaction();

    final List<TestOwner> resultList = session.createQuery("from TestOwner", TestOwner.class)
        .getResultList();

    transaction.commit();
    session.close();

    return resultList;
  }

  public void close() {
    factory.close();
  }

  public static void main(String[] args) {
    try (final AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(CreateConfig.class)) {
      System.out.println(context.getBean(Test.class).getOwners());
    }
  }
}
