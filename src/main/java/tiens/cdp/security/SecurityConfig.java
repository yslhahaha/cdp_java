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

    /**
     * 验证则优先进入
     * ResourceServerConfigurerAdapter进行token验证。而不会进行 WebSecurityConfigurerAdapter 的 basic auth或表单认证。
     * WebSecurityConfigurerAdapter是默认情况下spring security的http配置
     * ResourceServerConfigurerAdapter是默认情况下spring security oauth2的http配置
     * 此项目，API服务，所以不配置此处验证规则
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/login").permitAll()
//                .antMatchers("/hq").hasRole("USER1")
//                .anyRequest()
//                .authenticated();
    }

    @Override

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        auth.inMemoryAuthentication().passwordEncoder(encoder)
                .withUser("admin")
                .password(encoder.encode("admin")).roles("USER1");
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManager();
    }

}