package com.minicloud.busi.login.service;

/**
 * @Author alan.wang
 */
public interface TenantLoginService {


    String login(String username, String password) throws Exception;
}
