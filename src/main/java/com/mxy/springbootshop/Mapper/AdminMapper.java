package com.mxy.springbootshop.Mapper;

import com.mxy.springbootshop.POJO.Admin;
import com.mxy.springbootshop.POJO.Business;
import com.mxy.springbootshop.POJO.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminMapper {
    public int insertUser(Admin admin);

    public Admin getAdminByUsername(String username);

    public Admin adminLogin(@Param("username") String username, @Param("password") String password);

    public Admin getAdminByEmail(String email);
}
