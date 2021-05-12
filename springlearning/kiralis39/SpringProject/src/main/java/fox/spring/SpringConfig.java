package fox.spring;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;


@Configuration
@ComponentScan("fox.entities.pets")
@PropertySource("classpath:springProp.properties")
public class SpringConfig {
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
    
    
    static class DataBase {
        private static Connection conn;

        protected DataBase() {}
        
        public DataBase(Connection connection) throws ClassNotFoundException {
            conn = connection;
        }

        public void init() {
        	System.out.println("Data base is connected.");
        }

        public void closeDataBase() throws Exception {
            if (conn != null && !conn.isClosed()) {conn.close();}
        }
    }
}