package com.mxy.springbootshop.Service;

import com.mxy.springbootshop.Mapper.AdminMapper;
import com.mxy.springbootshop.Mapper.BusinessMapper;
import com.mxy.springbootshop.POJO.Admin;
import com.mxy.springbootshop.POJO.Business;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;

    public Admin getAdmin(String username){
        return adminMapper.getAdminByUsername(username);
    }

    public Admin adminLogin(String username,String password){
        return adminMapper.adminLogin(username,password);
    }

    public int insertAdmin(Admin admin){ return adminMapper.insertUser(admin);}

    public Admin getAdminByEmail(String email){return adminMapper.getAdminByEmail(email);}
}
