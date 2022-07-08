package com.mxy.springbootshop.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mxy.springbootshop.POJO.Business;
import com.mxy.springbootshop.POJO.Goods;
import com.mxy.springbootshop.POJO.User;
import com.mxy.springbootshop.Service.BusinessService;
import com.mxy.springbootshop.Service.GoodsService;
import com.mxy.springbootshop.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
public class AdminController {
    @Autowired
    GoodsService goodsService;

    @Autowired
    UserService userService;

    @Autowired
    BusinessService businessService;

    @GetMapping("/main.html")
    public String main(Model model){
        List<Goods> goods = goodsService.getAllGoods();
        log.info("全部商品"+goods);
        model.addAttribute("Goods",goods);
        return "main";
    }

    @GetMapping("/Goods.html")
    public String Goods(Model model){
        List<Goods> goods = goodsService.getAllGoods();
        log.info("全部商品"+goods);
        model.addAttribute("Goods",goods);
        return "Goods";
    }

    //商品下架
    @DeleteMapping("/deleteGoodById/{id}")
    public String deleteGoodById(@PathVariable("id") int id){
        goodsService.deleteGoodsById(id);
        return "redirect:/Goods.html";
    }

    //审核商品
    @PutMapping("/putGoods/{id}")
    public String putGoods(@PathVariable("id") int id){

        Goods good = goodsService.getGoodsById(id);
        log.info("正在审核的商品"+good);
        good.setChecked("已审核");
        goodsService.checkedGoods(good);
        return "redirect:/main.html";
    }

    //更改用户信息
    @PutMapping("/putUser/{id}")
    public String putUser(HttpSession session,@PathVariable("id") int id){
        session.setAttribute("UserId",id);
        return "PutUser";
    }
    @PutMapping("/putToUser")
    public String putToUser(User user,HttpSession session){
        int uid = (int) session.getAttribute("UserId");
        log.info("修改用户信息"+user);
        userService.updateUser(user,uid);
        return "redirect:/user";
    }

    @GetMapping("/signOffAdmin")
    public String signOffAdmin(HttpSession session){
        session.removeAttribute("loginAdmin");
        return "redirect:/login";
    }

    @RequestMapping("/userCheck")
    public String userCheck(Model model){
        List<User> userList = userService.getAllUsers();
        model.addAttribute("userList",userList);
        return "userCheck";
    }

    @PutMapping("/putUserState/{uid}")
    public String putUserState(@PathVariable("uid") int uid){
        User user = userService.getUserByUid(uid);
        user.setChecked("已审核");
        userService.updateUserState(user);
        return "redirect:/userCheck";
    }

    @RequestMapping("/businessCheck")
    public String businessCheck(Model model){
        List<Business> businessList = businessService.getAllBusiness();
        model.addAttribute("businessList",businessList);
        return "businessCheck";
    }

    @PutMapping("/putBusinessState/{bid}")
    public String putBusinessState(@PathVariable("bid") int bid){
        Business business =  businessService.getBusinessByBid(bid);
        business.setChecked("已审核");
        businessService.updateBusinessChecked(business);
        return "redirect:/businessCheck";
    }

    @RequestMapping("/add")
    public String addPage(){
        return "add";
    }
    @PostMapping("/addUser")
    public String addUser(User user,String password1,String password2,String role,Model model){
        if("买家".equals(role)){

                if(Objects.equals(password1, password2)){
                    user.setPassword(password1);
                    user.setChecked("未审核");
                    userService.insertUser(user);
                    model.addAttribute("msg","添加成功！");
                    return "redirect:/user";
                }else{
                    model.addAttribute("msg","密码输入不一致！");
                    return "add";
                }


        }else if("卖家".equals(role)){

            if(Objects.equals(password1, password2)){
                    Business business = new Business(user.getUid(),user.getUsername(), user.getTel(), user.getEmail(), user.getCity(), user.getSex(), user.getAccount(), user.getPassword(), user.getName1(),user.getChecked());
                    business.setPassword(password1);
                    business.setChecked("未审核");
                    log.info(business.toString());
                    businessService.insertBusiness(business);
                    model.addAttribute("msg","添加成功！");
                    return "redirect:/business_user";
                }else{
                    model.addAttribute("msg","密码输入不一致！");
                    return "add";
                }



        }
        return "add";
    }

    @GetMapping("/research")
    public String research(String researchInput,Model model,@RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                           @RequestParam(defaultValue="2",value="pageSize")Integer pageSize){

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
            List<User> userList = userService.getUserByResearchInput(researchInput);
            log.info("模糊查询到的用户："+userList);

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
}
