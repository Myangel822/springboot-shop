package com.mxy.springbootshop.Mapper;

import com.mxy.springbootshop.POJO.AccountBusiness;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountBusinessMapper {

    AccountBusiness getAccountBusinessByBid(int bid);

    void updateMoneyByBid(@Param("count") int count,@Param("bid") int bid);

    void updateMoneyByBidReturn(@Param("count") int count,@Param("bid") int bid);
}
