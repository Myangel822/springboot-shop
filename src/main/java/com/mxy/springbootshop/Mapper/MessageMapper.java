package com.mxy.springbootshop.Mapper;

import com.mxy.springbootshop.POJO.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper{

    void insertMessage(Message message);

    void updateMessageReturnDes(Message message);

    List<Message> getMessageByUid(int uid);

    List<Message> getMessageByBid(int bid);

    Message getMessageByOid(int oid);
}
