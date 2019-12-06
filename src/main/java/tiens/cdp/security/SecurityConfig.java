package tiens.cdp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;

import java.util.Objects;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/oauth/token").permitAll()
                .antMatchers("/user/*")
                .authenticated()

                .anyRequest()
                .permitAll()
                .and()
                .httpBasic()
                .and()
                //关闭跨站请求防护
                .csrf().disable();
    }

    @Override

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        auth.inMemoryAuthentication().passwordEncoder(encoder)
                .withUser("admin")
                .password(encoder.encode("admin")).roles("USER");
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManager();
    }

}