package com.mxy.springbootshop.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private int id;
    private int uid;
    private String goodName;
    private String des;
    private int number;
    private int price;
    private int count;
    private String checked;
    private String delivery;
    private int bid;
}
