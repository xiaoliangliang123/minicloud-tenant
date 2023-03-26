package com.minicloud.core.processer.dto;

import lombok.Data;

/**
 * @Author alan.wang
 * 事物消息类型，这里暂时没用上
 */
@Data
public class MessageTransationDTO {

    public static final Integer CREATE_ORDER = 1;

    private String id;
    private Integer type ;
    private String payload;
}
