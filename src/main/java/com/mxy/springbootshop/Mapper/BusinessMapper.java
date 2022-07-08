package com.mxy.springbootshop.Mapper;

import com.mxy.springbootshop.POJO.Business;
import com.mxy.springbootshop.POJO.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BusinessMapper {
    int insertBusiness(Business business);

    Business getBusinessByUsername(String username);

    Business businessLogin(@Param("username") String username, @Param("password") String password);

    Business getBusinessByEmail(String email);

    List<Business> getAllBusiness();

    void deleteBusinessByBid(int id);

    Business getBusinessByBid(int bid);

    void updateBusinessChecked(Business business);
}
