package tiens.cdp.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tiens.cdp.model.ApiUrl;
import tiens.cdp.model.LoginUser;

import java.util.List;

@Repository
public interface UserMapper {

    LoginUser findByUsername(String username);

    List<ApiUrl> findApiUrlByUsername(String username);
}
