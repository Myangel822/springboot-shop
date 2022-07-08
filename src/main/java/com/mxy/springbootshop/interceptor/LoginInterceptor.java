package com.mxy.springbootshop.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute("loginUser");
        Object loginBusiness = session.getAttribute("loginBusiness");
        Object loginAdmin = session.getAttribute("loginAdmin");
        if((!(loginUser == null))||(!(loginBusiness == null))||(!(loginAdmin == null))){
            return true;
        }
        request.setAttribute("msg","请先登录");
//        response.sendRedirect("/");
        request.getRequestDispatcher("/").forward(request,response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
