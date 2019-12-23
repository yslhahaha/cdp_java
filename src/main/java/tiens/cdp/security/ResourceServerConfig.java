package tiens.cdp.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

/**
 * 资源服务器配置
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    /**
     * 这里设置需要token验证的url
     * 这些url可以在WebSecurityConfigurerAdapter中排查掉，
     * 对于相同的url，如果二者都配置了验证则优先进入
     * ResourceServerConfigurerAdapter进行token验证。而不会进行 WebSecurityConfigurerAdapter 的 basic auth或表单认证。
     * WebSecurityConfigurerAdapter是默认情况下spring security的http配置
     * ResourceServerConfigurerAdapter是默认情况下spring security oauth2的http配置
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()//禁用了 csrf 功能
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/valcode").permitAll()
                .antMatchers("/druid/**").permitAll()
                .antMatchers("/favicon.ico").permitAll()
                .anyRequest()
                .authenticated();
    }
}
