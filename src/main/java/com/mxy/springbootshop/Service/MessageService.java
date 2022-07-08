package com.mxy.springbootshop.Service;

import com.mxy.springbootshop.Mapper.MessageMapper;
import com.mxy.springbootshop.POJO.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageMapper messageMapper;

    public void insertMessage(Message message){ messageMapper.insertMessage(message);}

    public void updateMessageReturnDes(Message message){messageMapper.updateMessageReturnDes(message);}

    public List<Message> getMessageByUid(int uid){return messageMapper.getMessageByUid(uid);}

    public List<Message> getMessageByBid(int bid){return messageMapper.getMessageByBid(bid);}

    public Message getMessageByOid(int oid){return messageMapper.getMessageByOid(oid);}
}
