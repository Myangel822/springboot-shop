package com.mxy.springbootshop.Service;

import com.mxy.springbootshop.Mapper.OrderMapper;
import com.mxy.springbootshop.POJO.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderMapper orderMapper;

    public int insertOrder(Order order){return orderMapper.insertOrder(order);}

    public List<Order> getOrderByUid(int id){return orderMapper.getOrderByUid(id);}

    public Order getOrderById(int id){return orderMapper.getOrderById(id);}

    public void  updateOrderChecked(Order order){orderMapper.updateOrderChecked(order);}

    public List<Order> getOrderByBid(int bid){return orderMapper.getOrderByBid(bid);}

    public void updateOrderDelivery(Order order){orderMapper.updateOrderDelivery(order);}
}
