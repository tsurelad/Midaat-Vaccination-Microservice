package vaccination.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

public abstract class DataBaseConfig {

    public static final String CACHE_NAME = "cache.database";
    public static final String CACHE_TTL = "${cache.database.timetolive:60}";

    public abstract DataSource dataSource();

    protected void configureDataSource(org.apache.tomcat.jdbc.pool.DataSource dataSource) {
        dataSource.setMaxActive(20);
        dataSource.setMaxIdle(8);
        dataSource.setMinIdle(8);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
    }
}

@Configuration
@Profile("mysql")
class MySQLDatabaseConfig extends DataBaseConfig {

    @Bean
    public DataSource dataSource() {
        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();

        String username = "username";
        String password = "password";
        String url = "jdbc:mysql://localhost:3306/dbname";

        URI dbUri;
        try {
            String dbProperty = System.getProperty("database.url");
            if(dbProperty != null) {
                dbUri = new URI(dbProperty);

                username = dbUri.getUserInfo().split(":")[0];
                password = dbUri.getUserInfo().split(":")[1];
                url = "jdbc:mysql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
            }
        } catch (URISyntaxException e) {
            //Deal with errors here.
        }

        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setValidationQuery("SELECT 1");

        configureDataSource(dataSource);

        return dataSource;
    }
}

@Configuration
@Profile("postgres")
class PostgresDatabaseConfig extends DataBaseConfig {

    @Bean
    public DataSource dataSource() {
        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();

        String username = "username";
        String password = "password";
        String url = "jdbc:postgresql://localhost/dbname";

        URI dbUri;
        try {
            String dbProperty = System.getProperty("database.url");
            if(dbProperty != null) {
                dbUri = new URI(dbProperty);

                username = dbUri.getUserInfo().split(":")[0];
                password = dbUri.getUserInfo().split(":")[1];
                url = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
            }
        } catch (URISyntaxException e) {
            //Deal with errors here.
        }

        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setValidationQuery("SELECT 1");

        configureDataSource(dataSource);

        return dataSource;
    }
}