package com.mxy.springbootshop.Service;

import com.mxy.springbootshop.Mapper.BusinessMapper;
import com.mxy.springbootshop.Mapper.UserMapper;
import com.mxy.springbootshop.POJO.Business;
import com.mxy.springbootshop.POJO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessService {
    @Autowired
    private BusinessMapper businessMapper;

    public Business getBusiness(String username){
        return businessMapper.getBusinessByUsername(username);
    }

    public Business businessLogin(String username,String password){
        return businessMapper.businessLogin(username,password);
    }

    public int insertBusiness(Business business){ return businessMapper.insertBusiness(business);}

    public Business getBusinessByEmail(String email){return businessMapper.getBusinessByEmail(email);}

    public List<Business> getAllBusiness(){return businessMapper.getAllBusiness();}

    public void deleteBusinessByBid(int id){businessMapper.deleteBusinessByBid(id);}

    public Business getBusinessByBid(int bid){return businessMapper.getBusinessByBid(bid);}

    public void updateBusinessChecked(Business business){businessMapper.updateBusinessChecked(business);}
}
