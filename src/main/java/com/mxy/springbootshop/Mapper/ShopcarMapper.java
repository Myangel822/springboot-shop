package com.mxy.springbootshop.Mapper;

import com.mxy.springbootshop.POJO.Goods;
import com.mxy.springbootshop.POJO.Shopcar;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopcarMapper {

    int insertGid(Shopcar shopcar);

    List<Goods> getGoods();

    void deleteGid(int id);
}
