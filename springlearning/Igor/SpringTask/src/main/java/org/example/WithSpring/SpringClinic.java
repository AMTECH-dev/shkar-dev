package org.example.WithSpring;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.example.WithSpring.animals.Dog;
import org.example.WithSpring.owner.Owner;
import org.example.WithSpring.util.Clinic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
@Configuration
@PropertySource("application.properties")
public class SpringClinic {
    @Value("${url}")
    private String url;
    @Value("${name}")
    private String username;
    @Value("${password}")
    private String password;

    @Bean
    public Connection connection() throws Exception {
        return DriverManager.getConnection(url, username, password);
    }

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringClinic.class);
//        Dog dog = context.getBean(Dog.class);
//        dog.say();
//        Owner owner = context.getBean(Owner.class);
//        System.out.println(owner.getName());
//        Clinic clinic = context.getBean(Clinic.class);
//        clinic.addNewDoctor();

        try (Connection connection = context.getBean(Connection.class)) {
            int id = 1;
            PreparedStatement statement = connection.prepareStatement("Select * from pet where id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }

            statement.close();
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        context.close();
    }
}
