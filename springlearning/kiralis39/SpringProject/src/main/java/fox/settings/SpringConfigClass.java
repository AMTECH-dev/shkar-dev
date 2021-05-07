package fox.settings;

import fox.clinics.PetClinic;
import fox.door.DataBase;
import fox.pets.AbstractPet;
import fox.pets.Cat;
import fox.pets.Dog;
import fox.pets.Fox;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
@ComponentScan("fox")
@PropertySource("classpath:springProp.properties")
public class SpringConfigClass {
    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;

    @Bean
    public Connection connection() throws Exception {
        return DriverManager.getConnection(url, username, password);
    }

    @Bean(initMethod = "init", destroyMethod = "closeDataBase")
    public DataBase dataBase(Connection connection) throws Exception {
        return new DataBase(connection);
    }
}