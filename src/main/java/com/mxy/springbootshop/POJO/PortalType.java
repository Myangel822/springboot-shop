package com.mxy.springbootshop.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

//首页类型
public class PortalType {
    private int id;
    private String type;
    private String des;

    //首页商品
    private List<PortalTypeGoods> portalTypeGoodsList;
}
