package tiens.cdp.dao;

import tiens.cdp.model.ApiUrl;

import java.util.List;

public interface ApiMapper {
    List<ApiUrl> findRoleApis();
}
