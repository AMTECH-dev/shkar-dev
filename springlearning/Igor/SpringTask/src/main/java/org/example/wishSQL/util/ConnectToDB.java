package org.example.wishSQL.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.example.wishSQL.SqlQuery;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@Configuration
@PropertySource("application.properties")
public class ConnectToDB {


  @Value("${name}")
  private String name;
  @Value("${password}")
  private String password;
  @Value("${url}")
  private String url;

  @Bean
  public Connection connection() throws SQLException {
    return DriverManager.getConnection(url, name, password);
  }

  @Bean( destroyMethod = "close")
  public SqlQuery sqlQuery(Connection connection) {
    return new SqlQuery(connection);
  }
}
