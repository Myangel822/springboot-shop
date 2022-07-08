package com.mxy.springbootshop.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mxy.springbootshop.POJO.*;
import com.mxy.springbootshop.Service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private ShopcarService shopcarService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private AccountService accountService;

    //管理员界面显示用户信息
    @RequestMapping("/user")
    public String userList(Model model,
                           @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                           @RequestParam(defaultValue="2",value="pageSize")Integer pageSize) {
        if(pageNum==null || pageNum<=0){
            pageNum = 1;
        }
        //设置默认每页显示的数据数
        if(pageSize == null){
            pageSize = 1;
        }
        log.info("当前页是："+pageNum+"显示条数是："+pageSize);

        PageHelper.startPage(pageNum,pageSize);

        try {
            List<User> userList = userService.getAllUsers();
            log.info("分页数据："+userList);

            PageInfo<User> pageInfo = new PageInfo<User>(userList,pageSize);
            log.info("PageNum:"+String.valueOf(pageInfo.getPageNum()));

            model.addAttribute("pageInfo",pageInfo);
            model.addAttribute("userList",userList);
        }finally {
            //清理 ThreadLocal 存储的分页参数,保证线程安全
            PageHelper.clearPage();
        }
        return "user";
    }

    @DeleteMapping("/deleteUser/{uid}")
    public String deleteUser(@PathVariable("uid") int id){
        userService.deleteUserByUid(id);
        return "redirect:/user";
    }

    @GetMapping("/signOffUser")
    public String signOff(HttpSession session){
        session.removeAttribute("loginUser");
        return "redirect:/login";
    }

    //跳转支付页面
    @RequestMapping("/pay-step-1/{id}")
    public String payStep1(Model model,@PathVariable("id") int id){
        Goods goods = goodsService.getGoodsById(id);
        model.addAttribute("Good",goods);
        return "pay-step-1";
    }

    //加入购物车
    @RequestMapping("/shopcar/{id}")
    public String shopcar(@PathVariable("id") int id,HttpSession session){
        User user = (User) session.getAttribute("loginUser");
        Shopcar shopcar = new Shopcar();
        shopcar.setUid(user.getUid());
        shopcar.setGid(id);
        shopcarService.insertGid(shopcar);
        return "redirect:/shopcar";
    }

    @RequestMapping("/order")
    public String orderPage(Model model,HttpSession session){
        User user = (User) session.getAttribute("loginUser");
        List<Order> orderList = orderService.getOrderByUid(user.getUid());
        log.info("我的订单："+orderList);
        model.addAttribute("orderList",orderList);
        model.addAttribute("Mark",user.getMark());

        Account account = accountService.getAccountByUid(user.getUid());
        model.addAttribute("Money",account.getMoney());

        return "order";
    }

}
