package tiens.cdp.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tiens.cdp.model.CdpUser;

@Repository
public interface UserMapper {
    CdpUser findByUsername(String username);
}
