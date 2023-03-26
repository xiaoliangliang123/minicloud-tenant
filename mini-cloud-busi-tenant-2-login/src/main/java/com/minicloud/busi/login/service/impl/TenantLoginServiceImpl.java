package com.minicloud.busi.login.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.http.HttpRequest;
import com.minicloud.busi.login.service.TenantLoginService;
import com.minicloud.common.constant.MiniCloudCommonConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.minicloud.common.constant.MiniCloudCommonConstant.ServerConstant.MINI_CLOUD_SERVER_AUTH;
import static com.minicloud.common.constant.MiniCloudCommonConstant.ServerConstant.MINI_CLOUD_SERVER_UPMS;

/**
 * @Author alan.wang
 */
@Service
public class TenantLoginServiceImpl implements TenantLoginService {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${tenant.login.scope}")
    private String scope;

    @Value("${tenant.login.grant_type}")
    private String grantType;

    @Value("${tenant.login.client_id}")
    private String clientId;

    @Value("${tenant.login.client_secret}")
    private String clientSecret;




    @Override
    public String login(String username, String password) throws Exception {

        List<ServiceInstance> serviceInstances =   discoveryClient.getInstances(MINI_CLOUD_SERVER_AUTH);
        for(ServiceInstance serviceInstance:serviceInstances){
           String response =  login(username,password,serviceInstance);

           if(null != response){
               return response;
           }
        }
        throw new Exception("null auth instance could be used");
    }

    private String login(String username, String password, ServiceInstance serviceInstance) {

        try {


            String url = "http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/oauth/token";
            Map map = MapUtil.builder().
                    put("username",username).
                    put("password",password).
                    put("scope",scope).
                    put("grant_type",grantType).
                    put("client_id",clientId).
                    put("client_secret",clientSecret).build();
            HttpRequest httpRequest = HttpRequest.post(url).form(map);
            return httpRequest.execute().body();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
}
