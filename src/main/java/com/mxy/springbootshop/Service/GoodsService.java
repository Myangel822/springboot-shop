package com.mxy.springbootshop.Service;

import com.mxy.springbootshop.Mapper.GoodsMapper;
import com.mxy.springbootshop.POJO.Goods;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    public void insertGoods(Goods good){goodsMapper.insertGoods(good);}

    public List<Goods> getGoodsByBid(int bid){return goodsMapper.getGoodsByBid(bid);}

    public List<Goods> getAllGoods(){return goodsMapper.getAllGoods();}

    public void deleteGoodByName(String name){goodsMapper.deleteGoodByName(name);}

    public void checkedGoods(Goods goods){
        goodsMapper.checkedGoods(goods);
    }

    public Goods getGoodsById(int id){return goodsMapper.getGoodsById(id);}

    public void updateSales(@Param("number") int number,@Param("id") int id){ goodsMapper.updateSales(number,id);}

    public void deleteGoodsById(int id){goodsMapper.deleteGoodById(id);}

    public List<Goods> getGoodsByResearchInput(String researchInput){return goodsMapper.getGoodsByResearchInput(researchInput);}
}
