package tiens.cdp.service.impl;

import org.springframework.stereotype.Service;
import tiens.cdp.dao.ApiMapper;
import tiens.cdp.model.ApiUrl;
import tiens.cdp.service.IApiService;

import java.util.List;

@Service
public class ApiServiceImpl implements IApiService {

    private ApiMapper apiMapper;

    public ApiServiceImpl(ApiMapper apiMapper) {
        this.apiMapper = apiMapper;
    }

    @Override
    public List<ApiUrl> findRoleApis() {
        return apiMapper.findRoleApis();
    }
}
