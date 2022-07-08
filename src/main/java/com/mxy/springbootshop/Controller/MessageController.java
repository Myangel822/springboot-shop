package com.mxy.springbootshop.Controller;

import com.mxy.springbootshop.POJO.Business;
import com.mxy.springbootshop.POJO.Message;
import com.mxy.springbootshop.POJO.Order;
import com.mxy.springbootshop.POJO.User;
import com.mxy.springbootshop.Service.MessageService;
import com.mxy.springbootshop.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MessageController {

    @Autowired
    OrderService orderService;
    @Autowired
    MessageService messageService;

    @RequestMapping("/postMessage/{id}")
    public String postMessage(@PathVariable("id") int id, Model model){
        Order order = orderService.getOrderById(id);
        model.addAttribute("order",order);
        return "postMessage";
    }

    @PostMapping("/messageUser/{id}")
    public String postMessageUser(@PathVariable("id") int id,String des){
        Order order = orderService.getOrderById(id);
        Message message = new Message();
        message.setBid(order.getBid());
        message.setDes(des);
        message.setUid(order.getUid());
        message.setOid(order.getId());
        message.setGoodName(order.getGoodName());
        messageService.insertMessage(message);

        return "redirect:/userMessage";
    }

    @RequestMapping("/userMessage")
    public String userMessage(HttpSession session, Model model){
        User user = (User) session.getAttribute("loginUser");
        List<Message> messageList = messageService.getMessageByUid(user.getUid());
        model.addAttribute("messageList",messageList);

        return "userMessage";
    }

    @RequestMapping("/message")
    public String message(Model model,HttpSession session){
        Business business = (Business) session.getAttribute("loginBusiness");
        List<Message> messageList = messageService.getMessageByBid(business.getBid());
        model.addAttribute("messageList",messageList);

        return "message";
    }

    @RequestMapping("/putMessage/{oid}")
    public String putMessage(@PathVariable("oid") int oid,Model model){
        Order order = orderService.getOrderById(oid);
        model.addAttribute("order",order);

        return "putMessage";
    }

    @PutMapping("/messageBusiness/{id}")
    public String messageBusiness(@PathVariable("id") int id,String returnDes){
        Order order = orderService.getOrderById(id);
        Message message = messageService.getMessageByOid(order.getId());

        message.setReturnDes(returnDes);
        messageService.updateMessageReturnDes(message);
        return "redirect:/message";
    }
}
