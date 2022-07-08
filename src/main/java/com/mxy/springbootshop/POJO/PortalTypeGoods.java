package com.mxy.springbootshop.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

//首页商品
public class PortalTypeGoods {

    private int id;
    private String goodName;
    private int price;
    private int sales;
    private int mark;
    private String headerFilePath;
}
