package fox.settings;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import fox.door.DataBase;


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