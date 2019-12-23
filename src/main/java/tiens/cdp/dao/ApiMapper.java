package tiens.cdp.dao;

import org.springframework.stereotype.Repository;
import tiens.cdp.model.ApiUrl;

import java.util.List;

@Repository
public interface ApiMapper {
    List<ApiUrl> findRoleApis();
}
