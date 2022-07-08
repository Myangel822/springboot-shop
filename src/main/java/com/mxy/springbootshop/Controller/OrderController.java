package com.mxy.springbootshop.Controller;

import com.mxy.springbootshop.POJO.*;
import com.mxy.springbootshop.Service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    AddressService addressService;
    @Autowired
    UserService userService;


    @RequestMapping("/pay-step-2/{id}")
    public String payStep2(HttpSession session, @PathVariable("id") int id, Integer number, Model model){
        User user = (User) session.getAttribute("loginUser");
        Goods goods = goodsService.getGoodsById(id);
        Order order = new Order();
        order.setUid(user.getUid());
        order.setGoodName(goods.getGoodName());
        order.setDes(goods.getDes());

        order.setNumber(number);
        order.setPrice(goods.getPrice());
        order.setCount(number*goods.getPrice());
        order.setChecked("未支付");
        order.setDelivery("未发货");
        order.setBid(goods.getBid());

        //更改商品数量和历史数量
        if(goods.getSales()<number){
            model.addAttribute("msg","您购买的商品没有剩余了");
            return "pay-step-1";
        }else{
            goodsService.updateSales(number,id);
        }

        log.info("当前订单："+order);
        orderService.insertOrder(order);
        model.addAttribute("Order",order);

        //更改用户积分
        userService.updateUserMark(goods.getMark(),user.getUid());
        log.info("更改积分后的用户信息："+user);

        //获取用户地址列表
        List<Address> address = addressService.getAddressByUid(user.getUid());
        log.info("当前用户收货地址"+address);
        model.addAttribute("Address",address);

        session.setAttribute("goodId",id);

        return "pay-step-2";
    }

    @PostMapping("/insertAddress/{id}")
    public String insertAddress(Address address1,HttpSession session,Model model,@PathVariable("id") int id){
        Order order = orderService.getOrderById(id);
        User user = (User) session.getAttribute("loginUser");

        address1.setUid(user.getUid());
        log.info("新插入的地址："+address1);
        addressService.insertAddress(address1);


        List<Address> address = addressService.getAddressByUid(user.getUid());
        log.info("当前用户收货地址"+address);
        model.addAttribute("Address",address);
        model.addAttribute("Order",order);
        return "pay-step-2";


    }


}
