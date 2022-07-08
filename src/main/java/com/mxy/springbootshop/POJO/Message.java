package com.mxy.springbootshop.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private int id;
    private String goodName;
    private String des;
    private String returnDes;
    private int uid;
    private int oid;
    private int bid;

}
