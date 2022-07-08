package com.mxy.springbootshop.Mapper;

import com.mxy.springbootshop.POJO.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsMapper {

    int insertGoods(Goods goods);

    List<Goods> getGoodsByBid(int bid);

    List<Goods> getAllGoods();

    void deleteGoodByName(String name);

    int checkedGoods(Goods goods);

    Goods getGoodsById(int id);

    void updateSales(@Param("number") int number,@Param("id") int id);

    void deleteGoodById(int id);

    List<Goods> getGoodsByResearchInput(String researchInput);
}
