package com.mxy.springbootshop.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mxy.springbootshop.POJO.*;
import com.mxy.springbootshop.Service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
public class BusinessController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private BusinessService businessService;
    @Autowired
    private AccountBusinessService accountBusinessService;
    @Autowired
    private OrderService orderService;


    @GetMapping("/center")
    public String center(HttpSession session, Model model){
        Business loginBusiness = (Business) session.getAttribute("loginBusiness");
        List<Goods> goods=goodsService.getGoodsByBid(loginBusiness.getBid());
        log.info("当前用户发布的商品"+goods);
        model.addAttribute("Goods",goods);

        AccountBusiness accountBusiness = accountBusinessService.getAccountBusinessByBid(loginBusiness.getBid());
        model.addAttribute("Money",accountBusiness.getMoney());
        return "center";
    }

    @GetMapping("/start")
    public String start(){
        return "start";
    }

    @GetMapping("/start-step-1")
    public String startStep1(){
        return "start-step-1";
    }

    @GetMapping("/business")
    public String mainPage(){
        return "business";
    }

    @GetMapping("/project")
    public String project(){
        return "project";
    }
//    @GetMapping("/start-step-2")
//    public String startStep2(){
//
//    }

    //发布商品
    @PostMapping("/insertGoods")
    public String insertGoods(Goods good, HttpSession session, @RequestParam("uploadFile") MultipartFile uploadFile) throws IOException {

        Business loginBusiness = (Business) session.getAttribute("loginBusiness");
        good.setBid(loginBusiness.getBid());
        String originalFilename = uploadFile.getOriginalFilename();
        int index = originalFilename.lastIndexOf('.') + 1;//获取地址.的前面的数字，从0开始
        String type = originalFilename.substring(index);//从地址.开始截取后缀
        //保存图片的路径（这是存在我项目中的images下了，你们可以设置路径）
        String filePath = "F:\\IDEA\\springboot-shop\\src\\main\\resources\\static\\images";
        //生成新文件名字
        String newFileName = UUID.randomUUID() + originalFilename;
        // 封装上传文件位置的全路径
        File targetFile = new File(filePath, newFileName);
        //把本地文件上传到封装上传文件位置的全路径
        uploadFile.transferTo(targetFile);
        good.setHeaderFilePath(newFileName);
        good.setChecked("未审核");
        log.info("新发布的商品"+good);
        if (type.equals("jpg") || type.equals("png")) {
            goodsService.insertGoods(good);
                return "start-step-2";
            }
        return "error";
    }

    @DeleteMapping("/deleteGoods/{name}")
    public String deleteGoods(@PathVariable("name") String name){
        log.info("要下架的商品是"+name);
        goodsService.deleteGoodByName(name);

        return "redirect:/center";
    }

    @GetMapping("/signOffBusiness")
    public String signOff(HttpSession session){
        session.removeAttribute("loginBusiness");
        return "redirect:/login";
    }

    @RequestMapping("/business_user")
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
            List<Business> businessList = businessService.getAllBusiness();
            log.info("分页数据："+businessList);

            PageInfo<Business> pageInfo = new PageInfo<Business>(businessList,pageSize);
            log.info("PageNum:"+String.valueOf(pageInfo.getPageNum()));

            model.addAttribute("pageInfo",pageInfo);
            model.addAttribute("businessList",businessList);
        }finally {
            //清理 ThreadLocal 存储的分页参数,保证线程安全
            PageHelper.clearPage();
        }
        return "business_user";
    }

    @DeleteMapping("/deleteBusiness/{bid}")
    public String deleteUser(@PathVariable("bid") int id){
        businessService.deleteBusinessByBid(id);
        return "redirect:/business_user";
    }

    //商家查看订单
    @GetMapping("/orderBusiness")
    public String orderBusiness(Model model,HttpSession session){
        Business business = (Business) session.getAttribute("loginBusiness");
        List<Order> orderList = orderService.getOrderByBid(business.getBid());
        model.addAttribute("orderList",orderList);

        AccountBusiness accountBusiness = accountBusinessService.getAccountBusinessByBid(business.getBid());
        model.addAttribute("Money",accountBusiness.getMoney());
        return "orderBusiness";
    }

    //发货
    @PutMapping("/delivery/{id}")
    public String delivery(@PathVariable("id") int id){
        Order order = orderService.getOrderById(id);
        order.setDelivery("已发货");
        orderService.updateOrderDelivery(order);

        return "redirect:/orderBusiness";
    }



}
