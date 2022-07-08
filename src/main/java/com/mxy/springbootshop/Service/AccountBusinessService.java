package com.mxy.springbootshop.Service;

import com.mxy.springbootshop.Mapper.AccountBusinessMapper;
import com.mxy.springbootshop.POJO.AccountBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountBusinessService {

    @Autowired
    AccountBusinessMapper accountBusinessMapper;

    public AccountBusiness getAccountBusinessByBid(int bid){ return accountBusinessMapper.getAccountBusinessByBid(bid);}

    public void updateMoneyByBid(int count,int bid){accountBusinessMapper.updateMoneyByBid(count,bid);}

    public void updateMoneyByBidReturn(int count,int bid){accountBusinessMapper.updateMoneyByBidReturn(count,bid);}

}
