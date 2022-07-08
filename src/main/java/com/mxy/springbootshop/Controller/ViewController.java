package com.mxy.springbootshop.Controller;

import com.mxy.springbootshop.POJO.Admin;
import com.mxy.springbootshop.POJO.Business;
import com.mxy.springbootshop.POJO.User;
import com.mxy.springbootshop.Service.AdminService;
import com.mxy.springbootshop.Service.BusinessService;
import com.mxy.springbootshop.Service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Slf4j
@Controller
public class ViewController {

    @Autowired
    UserService userService;
    @Autowired
    BusinessService businessService;
    @Autowired
    AdminService adminService;

    @GetMapping({"/login","/"})
    public String login(){
        return "login";
    }

    //登录
    @PostMapping("/login")
    public String main(User user,String code, HttpSession session, Model model, String role){
        log.info("输入的用户名和密码： "+user.getUsername()+","+user.getPassword());
        log.info("角色：" + role);
        // 获取session中的验证码
        String sessionCode = (String) session.getAttribute("captcha");
        // 判断验证码
        if (code==null || !sessionCode.equals(code.trim().toLowerCase())) {
            model.addAttribute("msg","验证码不正确");
            return "login";
        }
        if("买家".equals(role)){
            User user1 = userService.userLogin(user.getUsername(), user.getPassword());
            log.info("登录用户信息："+user1);
            if((!(user1 == null))&&("已审核".equals(user1.getChecked()))){
                session.setAttribute("loginUser",user1);
                return "redirect:/index.html";
            }else{
                model.addAttribute("msg","账号或密码错误 或未经过审核");
                return "login";
            }
        }else if("卖家".equals(role)){
            Business business = businessService.businessLogin(user.getUsername(), user.getPassword());
            if((!(business == null))&&("已审核".equals(business.getChecked()))){
                log.info(business.toString());
                session.setAttribute("loginBusiness",business);
                return "redirect:/business.html";
            }else{
                model.addAttribute("msg","账号或密码错误 或未经过审核");
                return "login";
            }

        }else if("管理员".equals(role)){
            Admin admin = adminService.adminLogin(user.getUsername(), user.getPassword());
            if(!(admin == null)){
                session.setAttribute("loginAdmin",admin);
                return "redirect:/main.html";
            }else{
                model.addAttribute("msg","账号或密码错误");
                return "login";
            }
        }else{
            return  "login";
        }



    }

    @GetMapping("/registration")
    public String registrationPage(){
        return "registration";
    }

    //注册
    @PostMapping("/registration")
    public String registration(User user,String password1,String password2,String checked,String role,Model model){
        log.info(user.toString());
        log.info(String.valueOf(checked));
        if("买家".equals(role)){
            if(!(checked==null)){
                if(Objects.equals(password1, password2)){
                    user.setPassword(password1);
                    user.setChecked("未审核");
                    userService.insertUser(user);
                    model.addAttribute("msg","注册成功！");
                    return "login";
                }else{
                    model.addAttribute("msg","密码输入不一致！");
                    return "registration";
                }
            }
            model.addAttribute("msg","请同意协议");
            return "registration";
        }else if("卖家".equals(role)){
            if(!(checked==null)){
                if(Objects.equals(password1, password2)){
                    Business business = new Business(user.getUid(),user.getUsername(), user.getTel(), user.getEmail(), user.getCity(), user.getSex(), user.getAccount(), user.getPassword(), user.getName1(),user.getChecked());
                    business.setPassword(password1);
                    business.setChecked("未审核");
                    log.info(business.toString());
                    businessService.insertBusiness(business);
                    model.addAttribute("msg","注册成功！");
                    return "login";
                }else{
                    model.addAttribute("msg","密码输入不一致！");
                    return "registration";
                }
            }
            model.addAttribute("msg","请同意协议");
            return "registration";
        }
        return "registration";

    }



}
