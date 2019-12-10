package tiens.cdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tiens.cdp.dao.UserMapper;
import tiens.cdp.model.CdpUser;

import java.util.HashSet;
import java.util.Set;

@Service
public class CdpUserDetailService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;


//    public CdpUserDetailService(UserMapper userMapper){
//        this.userMapper = userMapper;
//    }


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        PUser user = userDao.findByMemberName(userName);
//        if (user == null) {
//            throw new UsernameNotFoundException(userName);
//        }
        CdpUser user1 = userMapper.findByUsername(userName);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        // 可用性 :true:可用 false:不可用
        boolean enabled = true;
        // 过期性 :true:没过期 false:过期
        boolean accountNonExpired = true;
        // 有效性 :true:凭证有效 false:凭证无效
        boolean credentialsNonExpired = true;
        // 锁定性 :true:未锁定 false:已锁定
        boolean accountNonLocked = true;
        /*for (Role role : member.getRoles()) {
            //角色必须是ROLE_开头，可以在数据库中设置
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRoleName());
            grantedAuthorities.add(grantedAuthority);
            //获取权限
            for (Permission permission : role.getPermissions()) {
                GrantedAuthority authority = new SimpleGrantedAuthority(permission.getUri());
                grantedAuthorities.add(authority);
            }
        }*/
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_USER1");
        grantedAuthorities.add(grantedAuthority);


        User user = new User("admin", "$2a$10$G2OSkl9g5zBFRA.xFBtVzOziSc5zpfDZpoNf/BpFOWLFM.r7e0p/q",
                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);
        return user;
    }
}
