package com.mxy.springbootshop.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class Goods {
    private int id;
    private String goodName;
    private String des;
    private String headerFilePath;
    private int price;
    private String type;
    private int sales;
    private int history;
    private int mark;
    private int bid;
    private String checked;
}
