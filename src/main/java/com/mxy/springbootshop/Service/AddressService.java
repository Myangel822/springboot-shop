package com.mxy.springbootshop.Service;

import com.mxy.springbootshop.Mapper.AddressMapper;
import com.mxy.springbootshop.POJO.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    AddressMapper addressMapper;

    public List<Address> getAddressByUid(int uid){
        return addressMapper.getAddressByUid(uid);
    }

    public int insertAddress(Address address){
        return addressMapper.insertAddress(address);
    }
}
