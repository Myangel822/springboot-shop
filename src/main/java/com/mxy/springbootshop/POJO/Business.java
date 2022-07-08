package com.mxy.springbootshop.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class Business {
    private int bid;
    private String username;
    private String tel;
    private String email;
    private String city;
    private String sex;
    private String account;
    private String password;
    private String name1;
    private String checked;
}
