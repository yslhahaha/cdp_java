package tiens.cdp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiens.cdp.dao.UserMapper;
import tiens.cdp.model.ApiUrl;
import tiens.cdp.model.LoginUser;
import tiens.cdp.service.IUserService;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {


    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public LoginUser findByUsername(String username) {

        LoginUser user = userMapper.findByUsername(username);

        List<ApiUrl> apis = userMapper.findApiUrlByUsername(username);

        StringBuilder userRoles = new StringBuilder("");

        user.setApis(apis);

        return user;
    }
}
