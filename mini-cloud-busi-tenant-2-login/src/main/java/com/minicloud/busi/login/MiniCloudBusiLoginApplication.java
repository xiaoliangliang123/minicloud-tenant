package com.minicloud.busi.login;

import com.minicloud.common.fegin.EnableMiniCloudFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;


@SpringCloudApplication
@EnableCaching
@EnableMiniCloudFeignClients
public class MiniCloudBusiLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniCloudBusiLoginApplication.class, args);
    }

}
