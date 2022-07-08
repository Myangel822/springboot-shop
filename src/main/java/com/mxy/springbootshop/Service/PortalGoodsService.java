package com.mxy.springbootshop.Service;

import com.mxy.springbootshop.Mapper.PortalTypeGoodsMapper;
import com.mxy.springbootshop.POJO.PortalType;
import com.mxy.springbootshop.POJO.PortalTypeGoods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PortalGoodsService {
    @Autowired
    PortalTypeGoodsMapper portalGoodsMapper;

    public List<PortalType> getPortalType(){return portalGoodsMapper.getPortalType();}

//    public List<PortalTypeGoods> getPortalTypeGoods(){return portalGoodsMapper.getPortalTypeGoods();}
}
