package com.mxy.springbootshop.Mapper;

import com.mxy.springbootshop.POJO.Address;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AddressMapper {

    List<Address> getAddressByUid(int uid);

    int insertAddress(Address address);
}
