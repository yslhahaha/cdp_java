package tiens.cdp.service;

import tiens.cdp.model.LoginUser;

public interface IUserService {
    LoginUser findByUsername(String username);
}
