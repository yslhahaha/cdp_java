package tiens.cdp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * 认证服务器配置
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    /**
     * 注入权限验证控制器 来支持 password grant type
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 注入userDetailsService，开启refresh_token需要用到
     */
    //@Autowired
    //private MyUserDetailsService userDetailsService;

    /**
     * 数据源
     */
//    @Autowired
//    private DataSource dataSource;

    /**
     * 设置保存token的方式，一共有五种，这里采用数据库的方式
     */
    //@Autowired
    //private TokenStore tokenStore;

//    @Autowired
//    private WebResponseExceptionTranslator webResponseExceptionTranslator;

    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        security.passwordEncoder(passwordEncoder())
//                .checkTokenAccess("permitAll()")
//                .allowFormAuthenticationForClients();//支持把secret和clientid写在url上，否则需要在头上
        super.configure(security);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("tonr").secret(passwordEncoder().encode("secret"))
                .autoApprove(true)   //test
                .authorizedGrantTypes("authorization_code", "refresh_token",
                        "password", "implicit")
                .scopes("read","write","del","userinfo");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager);

    }

    /**
     * 密码加密
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
