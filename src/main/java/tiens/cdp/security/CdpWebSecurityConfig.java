package tiens.cdp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.DigestUtils;
import tiens.cdp.service.CdpUserDetailService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Configuration
@EnableWebSecurity
public class CdpWebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CdpUserDetailService userDetailService;

    @Autowired
    public CdpWebSecurityConfig(CdpUserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    /**
     * 验证则优先进入
     * ResourceServerConfigurerAdapter进行token验证。而不会进行 WebSecurityConfigurerAdapter 的 basic auth或表单认证。
     * WebSecurityConfigurerAdapter是默认情况下spring security的http配置
     * ResourceServerConfigurerAdapter是默认情况下spring security oauth2的http配置
     * 此项目，API服务，所以不配置此处验证规则
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

    }

    @Override

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return Objects.equals(charSequence.toString(), s);
            }
        };
    }
}