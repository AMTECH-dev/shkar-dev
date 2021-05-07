package org.example.wishSQL;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.example.wishSQL.util.ConnectToDB;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class SqlQuery {
  private Connection connection;

  public SqlQuery(Connection connection) {
    this.connection = connection;
  }

  public Connection getConnection() {
    return connection;
  }

  public void close() throws Exception {
    System.out.printf("ghjdthrf");
    connection.close();
  }

  public static void main(String[] args) throws SQLException {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
        ConnectToDB.class);


    Connection connection = context.getBean(SqlQuery.class).getConnection();
      Statement statement = connection.createStatement();

      //   statement.executeUpdate("INSERT INTO pvirus(\"petId\", \"virusId\") VALUES(1,3),(5,2),(4,1),(4,3),(5,4)");
      //  System.out.println(statement.executeUpdate(
         //  "UPDATE owner SET age = 99 where id = (Select \"ownerId\" from pet where name = 'Biden')"));
String query1 ="UPDATE owner SET age = 5 where id = (Select \"ownerId\" from pet where name = 'Biden')";
String query2 ="UPDATE owner SET age = 44 where id = (Select \"ownerId\" from pet where name = 'Obama')";
String query3 ="UPDATE owner SET age = 16 where id = (Select \"ownerId\" from pet where name = 'Jonson')";

    try {

      connection.setAutoCommit(false);

    statement.addBatch(query1);
statement.addBatch(query2);
statement.addBatch(query3);
statement.executeBatch();
connection.commit();
  } catch (SQLException throwables) {
    throwables.printStackTrace();
      try {
        connection.rollback();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }


      statement.close();

    context.close();


  }
}
