package com.mxy.springbootshop.Mapper;

import com.mxy.springbootshop.POJO.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountMapper {
     Account getMoneyByUid(@Param("uid") int uid);

     Account getObjectByAccountAndPassword(@Param("account") String account,@Param("password") String password);

     void updateAccountMoney(@Param("uid") int uid,@Param("count") int count);

     void updateAccountMoneyReturn(@Param("uid") int uid,@Param("count") int count);
}
