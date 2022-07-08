package com.mxy.springbootshop.Mapper;

import com.mxy.springbootshop.POJO.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {

    int insertOrder(Order order);

    List<Order> getOrderByUid(int id);

    Order getOrderById(int id);

    void updateOrderChecked(Order order);

    List<Order> getOrderByBid(int bid);

    void updateOrderDelivery(Order order);


}
