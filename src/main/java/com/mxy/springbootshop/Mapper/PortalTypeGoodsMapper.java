package com.mxy.springbootshop.Mapper;

import com.mxy.springbootshop.POJO.PortalType;
import com.mxy.springbootshop.POJO.PortalTypeGoods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PortalTypeGoodsMapper {

    List<PortalType> getPortalType();

//    List<PortalTypeGoods> getPortalTypeGoods();
}
