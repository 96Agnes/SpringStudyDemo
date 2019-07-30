package com.example.test.springtest.dto;

import lombok.Data;
/*
 *created by ZRH on 2019/6/2
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
