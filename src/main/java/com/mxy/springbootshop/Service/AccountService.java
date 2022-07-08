package com.mxy.springbootshop.Service;

import com.mxy.springbootshop.Mapper.AccountMapper;
import com.mxy.springbootshop.POJO.Account;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class AccountService {
    @Autowired
    private AccountMapper accountMapper;

    public Account getAccountByUid(int uid){
        return accountMapper.getMoneyByUid(uid);
    }

    public Account getObjectByAccountAndPassword(String account,String password){return accountMapper.getObjectByAccountAndPassword(account,password);}

    public void updateAccountMoney(int uid,int count){accountMapper.updateAccountMoney(uid,count);}

    public void updateAccountMoneyReturn(int uid,int count){accountMapper.updateAccountMoneyReturn(uid,count);}
}
