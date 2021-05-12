/*
package org.example.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class CreateConfig {
  @Bean
  public SessionFactory factory() {
    return new Configuration()
        .configure()
        .addAnnotatedClass(Owner.class)
        .buildSessionFactory();
  }

  @Bean(destroyMethod = "close")
  public Test test(SessionFactory sessionFactory) {
    return new Test(sessionFactory);
  }
}
*/
