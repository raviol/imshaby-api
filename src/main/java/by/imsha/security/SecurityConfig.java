package by.imsha.security;

import com.auth0.spring.security.api.JwtWebSecurityConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * @author Alena Misan
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Value("${spring.profiles.active}")
    private String env;

    @Value(value = "${auth0.audience}")
    private String apiAudience;

    @Value(value = "${auth0.issuer}")
    private String issuer;

    @Value(value = "${auth0.secret}")
    private String secret;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        authorizeRequests(http);
        JwtWebSecurityConfigurer
                .forRS256(apiAudience, issuer)
                .configure(http);
    }


    /**
     * Our API Configuration - for Profile CRUD operations
     * <p>
     * Here we choose not to bother using the `auth0.securedRoute` property configuration
     * and instead ensure any unlisted endpoint in our config is secured by default
     */
    protected void authorizeRequests(final HttpSecurity http) throws Exception {

        if (env.equals("production")) {
//        http.csrf().disable();
//        http.cors();

            http.authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/api/mass/week").permitAll()
                    .anyRequest().authenticated();
            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        }

    }

}
