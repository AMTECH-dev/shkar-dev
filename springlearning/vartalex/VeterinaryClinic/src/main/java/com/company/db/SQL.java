package com.company.db;

import java.sql.*;

public class SQL {
    public static void connectToBase(String url, String user, String pass, String SQLQuery) {
        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(SQLQuery);
                while (resultSet.next()) {
                    String petName = resultSet.getString("name");
                    System.out.println(petName);
                }
                resultSet.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void insertDB(String url, String user, String pass, String tableName, String nameOfColumn, String value) {
//        try {
//            Connection connection = DriverManager.getConnection(url, user, pass);
//            PreparedStatement statement = connection.prepareStatement("insert into " + tableName + "(" + nameOfColumn + ") values(" + value + ")");
//
//            statement.executeUpdate();
//            statement.close();
//            connection.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public static void addColumn(String url, String user, String pass, String SQLQuery) {
        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            try (PreparedStatement statement = connection
                    .prepareStatement("ALTER TABLE tableName ADD COLUMN column_name DataType")) {
                statement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
