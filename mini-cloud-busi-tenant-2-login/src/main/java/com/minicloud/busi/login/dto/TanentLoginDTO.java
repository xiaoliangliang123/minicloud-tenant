package com.minicloud.busi.login.dto;

import lombok.Data;

/**
 * @Author alan.wang
 */
@Data
public class TanentLoginDTO {

    String username;

    String password;

    String grant_type;

    String scope;

    String client_id;

    String client_secret;
}
