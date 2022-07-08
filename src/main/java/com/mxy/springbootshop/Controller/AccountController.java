package com.mxy.springbootshop.Controller;

import com.mxy.springbootshop.POJO.Account;
import com.mxy.springbootshop.POJO.Goods;
import com.mxy.springbootshop.POJO.Order;
import com.mxy.springbootshop.POJO.User;
import com.mxy.springbootshop.Service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
public class AccountController {
    @Autowired
    AccountService accountService;
    @Autowired
    ShopcarService shopcarService;


    @Autowired
    OrderService orderService;

    @Autowired
    AccountBusinessService accountBusinessService;
    User user;
    Account account;


    //跳转购物车，显示信息
    @GetMapping("/shopcar")
    public String adminPage(HttpSession session, Model model) {
        user = (User) session.getAttribute("loginUser");
        log.info("当前用户：" + user);

        account = accountService.getAccountByUid(user.getUid());
        log.info("用户钱包：" + account);

        List<Goods> goodsList = shopcarService.getGoods();
        model.addAttribute("goods", goodsList);
        log.info("购物车列表：" + goodsList);
        model.addAttribute("Mark", user.getMark());
        model.addAttribute("Money", account.getMoney());
        return "shopcar";
    }

    //购物车删除
    @DeleteMapping("/deleteShopCar/{id}")
    public String deleteShopCar(@PathVariable("id") int id) {
        shopcarService.deleteGid(id);
        return "redirect:/shopcar";
    }

    //支付
    @GetMapping("/pay-step-3/{id}")
    public String payStep3(Model model, @PathVariable("id") int id) {
        Order order = orderService.getOrderById(id);
        model.addAttribute("order", order);
        return "pay-step-3";
    }

    //立即支付
    @PutMapping("/pay/{id}")
    public String pay(String account, String password, @PathVariable("id") int id, HttpSession session) {

        Account account1 = accountService.getObjectByAccountAndPassword(account, password);
        log.info("支付账号密码：" + account);
        User user = (User) session.getAttribute("loginUser");
        Order order = orderService.getOrderById(id);
        if (!(account1 == null)) {
            order.setChecked("已支付");
            orderService.updateOrderChecked(order);

            //买家扣款
            accountService.updateAccountMoney(user.getUid(), order.getCount());

            //商家收款
            accountBusinessService.updateMoneyByBid(order.getCount(), order.getBid());

        }
        return "pay-step-4";
    }

    //退货
    @PutMapping("/return/{id}")
    public String returnOrder(@PathVariable("id") int id) {
        Order order = orderService.getOrderById(id);


            if ("未支付".equals(order.getChecked())) {
                order.setDelivery("已退货");
                orderService.updateOrderDelivery(order);
            } else if ("已支付".equals(order.getChecked())) {
                order.setDelivery("已退货");
                order.setChecked("已退款");
                orderService.updateOrderDelivery(order);
                orderService.updateOrderChecked(order);

                accountBusinessService.updateMoneyByBidReturn(order.getCount(), order.getBid());
                accountService.updateAccountMoneyReturn(order.getUid(), order.getCount());
            }

        return "redirect:/order";


    }
}
