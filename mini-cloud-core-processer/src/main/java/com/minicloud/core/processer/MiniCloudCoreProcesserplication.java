package com.minicloud.core.processer;

import com.minicloud.common.cache.EnableMiniCloudRedis;
import com.minicloud.common.fegin.EnableMiniCloudFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;


@EnableMiniCloudRedis
@SpringCloudApplication
@EnableCaching
@EnableMiniCloudFeignClients
public class MiniCloudCoreProcesserplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniCloudCoreProcesserplication.class, args);
    }

}
