package tiens.cdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tiens.cdp.model.ApiUrl;
import tiens.cdp.model.LoginUser;

import java.util.HashSet;
import java.util.Set;

@Service
public class CdpUserDetailService implements UserDetailsService {

    //@Autowired
    private IUserService userService;

    @Autowired
    public CdpUserDetailService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        try {
            LoginUser cdpUser = userService.findByUsername(userName);

            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            // 可用性 :true:可用 false:不可用
            boolean enabled = true;
            // 过期性 :true:没过期 false:过期
            boolean accountNonExpired = true;
            // 有效性 :true:凭证有效 false:凭证无效
            boolean credentialsNonExpired = true;
            // 锁定性 :true:未锁定 false:已锁定
            boolean accountNonLocked = true;

            for (ApiUrl api : cdpUser.getApis()) {
                //角色必须是ROLE_开头，可以在数据库中设置
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + api.getApiId());
                grantedAuthorities.add(grantedAuthority);
            }

            User user = new User(cdpUser.getUsername(), cdpUser.getPassword(),
                    enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);
            return user;


            //return new CdpUserDetails(user);
        } catch (Exception e) {
            throw new UsernameNotFoundException("user select fail");
        }
    }
}
