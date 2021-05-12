package com.company.ConnectJDBC;

import java.sql.*;

public class Connect {
    private Connection connection;

    public static void main(String[] args) {
        try {
            Connect connect=new Connect();
            connect.query();
            connect.statement();
            connect.prepareStatement();
            //connect.batchStatement();
            connect.batchPreparedStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void query() {
        {
            try {
                System.out.println("Connected to PostgreSQL");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Animals");
                while (resultSet.next()) {
                    System.out.printf("%-30.30s  %-30.30s%n", resultSet.getString("name"), resultSet.getString("age"));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void statement() {
        try {
            String sqlCommand = "CREATE TABLE IF NOT EXISTS products (Id SERIAL PRIMARY KEY, ProductName VARCHAR(20), Price INT)";
            Statement statementCommand = connection.createStatement();
            statementCommand.executeUpdate(sqlCommand);
            System.out.println("Database has been created!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void prepareStatement() {
        try {
            String name="Name1";
            int price=10;

            String sql = "INSERT INTO Products (ProductName, Price) Values (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, price);

            int rows = preparedStatement.executeUpdate();

            System.out.printf("%d rows added\n", rows);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void batchStatement() {
        try {
            Statement statement=connection.createStatement();
            for(int i=0;i<100;i++) {
                String sql = "INSERT INTO Products (ProductName, Price) Values ('name"+i+"', "+i+")";
                statement.addBatch(sql);
            }
            statement.executeBatch();
            statement.close();
            System.out.println("NNN");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void batchPreparedStatement() {
        try {
            String sql = "INSERT INTO Products (ProductName, Price) Values (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for(int i=0;i<100;i++) {
                preparedStatement.setString(1, "name"+i);
                preparedStatement.setInt(2, i);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            preparedStatement.close();
            System.out.println("NNN");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Connect() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*Properties props = new Properties();
        try(InputStream in = Files.newInputStream(Paths.get("database.properties"))){
            props.load(in);
        }
        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");

        return DriverManager.getConnection(url, username, password);*/
        try {
            connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/pet_clinic", "shkar", "shkar");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
