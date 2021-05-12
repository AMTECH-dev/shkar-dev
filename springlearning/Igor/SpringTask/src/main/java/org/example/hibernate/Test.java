package org.example.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test {
 /* private final SessionFactory factory;

  @Autowired
  public Test(SessionFactory factory) {
    this.factory = factory;
  }

  public SessionFactory getFactory() {
    return factory;
  }

  public List<Owner> getOwners() {
    final Session session = factory.openSession();
    final Transaction transaction = session.beginTransaction();

    final List<Owner> resultList = session.createQuery("from TestOwner", Owner.class)
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
*/


  public static void main(String[] args) {
    SessionFactory factory = new Configuration()
        .configure("hibernate.cfg.xml")
        .addAnnotatedClass(Owner.class)
        .addAnnotatedClass(Pet.class)
        .addAnnotatedClass(Doctor.class)
        .addAnnotatedClass(Virus.class)
        .buildSessionFactory();
    Session session = null;
    try {
      session = factory.openSession();
    /*  Owner owner = new Owner("Igor", 35, 9843608588l);
      Pet pet = new Pet("Oskar", 7, false,  "dog");
      Pet pet2 = new Pet("Bet", 5, false,  "cat");
      owner.addPetToOwner(pet);
      owner.addPetToOwner(pet2);*/

      Doctor doctor = new Doctor("Cap.", "America", "soldier");
      Owner owner = new Owner("Vanda", 28, 1232336655l);

//      Virus virus = new Virus("Blohi");
//      Pet pet = new Pet("Gitler", 99, true, "covid");
      session.beginTransaction();
      /*   session.save(owner);*/
        session.save(doctor);
    //  session.save(pet);
      session.getTransaction().commit();
    } finally {
      session.close();
      factory.close();
    }


  }
}
