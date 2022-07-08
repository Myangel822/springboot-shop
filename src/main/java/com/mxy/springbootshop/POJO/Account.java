package com.mxy.springbootshop.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private int id;
    private int uid;
    private String account;
    private String password;
    private int money;
}
