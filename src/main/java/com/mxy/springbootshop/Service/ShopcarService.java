package com.mxy.springbootshop.Service;

import com.mxy.springbootshop.Mapper.ShopcarMapper;
import com.mxy.springbootshop.POJO.Goods;
import com.mxy.springbootshop.POJO.Shopcar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopcarService {

    @Autowired
    ShopcarMapper shopcarMapper;

    public int insertGid(Shopcar shopcar){
        return shopcarMapper.insertGid(shopcar);
    }

    public List<Goods> getGoods(){ return shopcarMapper.getGoods();}

    public void deleteGid(int id){shopcarMapper.deleteGid(id);}
}
