package vaccination.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import javax.sql.DataSource;

/**
 * Authentication Security Definitions
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class AuthenticationSecurity extends GlobalAuthenticationConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(this.dataSource);
    }


}
