package com.mxy.springbootshop.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mxy.springbootshop.POJO.Goods;
import com.mxy.springbootshop.POJO.PortalType;
import com.mxy.springbootshop.Service.GoodsService;
import com.mxy.springbootshop.Service.PortalGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
public class PortalGoodsController {

    @Autowired
    PortalGoodsService portalGoodsService;

    @Autowired
    GoodsService goodsService;

    @GetMapping("/index.html")
    public String portalUserGoods(Model model){
        List<PortalType> portalType= portalGoodsService.getPortalType();
        log.info("显示数据 "+portalType);
        model.addAttribute("portalType",portalType);
        return "index";
    }

    @GetMapping("/business.html")
    public String portalBusinessGoods(Model model){
        List<PortalType> portalType= portalGoodsService.getPortalType();
        log.info("显示数据 "+portalType);
        model.addAttribute("portalType",portalType);
        return "business";
    }

    @GetMapping("/project/{id}")
    public String project(Model model, @PathVariable("id") int id){

        Goods goods = goodsService.getGoodsById(id);
        log.info("当前详细显示的商品："+goods);
        model.addAttribute("Good",goods);
        return "project";
    }

    @GetMapping("/projects")
    public String projects(Model model,@RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                           @RequestParam(defaultValue="4",value="pageSize")Integer pageSize){

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
            List<Goods> goods = goodsService.getAllGoods();
            PageInfo<Goods> pageInfo = new PageInfo<Goods>(goods,pageSize);
            log.info("PageNum:"+String.valueOf(pageInfo.getPageNum()));

            model.addAttribute("pageInfo",pageInfo);
            model.addAttribute("goods",goods);
        }finally {
            //清理 ThreadLocal 存储的分页参数,保证线程安全
            PageHelper.clearPage();
        }
        return "projects";
    }

    @GetMapping("/researchGoods")
    public String researchGoods(Model model,String researchInput,@RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                                @RequestParam(defaultValue="4",value="pageSize")Integer pageSize){

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
            List<Goods> goods = goodsService.getGoodsByResearchInput(researchInput);


            PageInfo<Goods> pageInfo = new PageInfo<Goods>(goods,pageSize);
            log.info("PageNum:"+String.valueOf(pageInfo.getPageNum()));

            model.addAttribute("pageInfo",pageInfo);
            model.addAttribute("goods",goods);
        }finally {
            //清理 ThreadLocal 存储的分页参数,保证线程安全
            PageHelper.clearPage();
        }
        return "projects";
    }
}
