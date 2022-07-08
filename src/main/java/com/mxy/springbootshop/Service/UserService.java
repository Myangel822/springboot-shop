package com.mxy.springbootshop.Service;

import com.mxy.springbootshop.Mapper.UserMapper;
import com.mxy.springbootshop.POJO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUser(String username){
        return userMapper.getUserByUsername(username);
    }

    public User userLogin(String username,String password){
        return userMapper.userLogin(username,password);
    }

    public int insertUser(User user){ return userMapper.insertUser(user);}

    public User getUserByUid(int id){return userMapper.getUserByUid(id);}

    public List<User> getAllUsers(){return userMapper.getAllUsers();}

    public void deleteUserByUid(int id){userMapper.deleteUserByUid(id);}

    public void updateUser(User user,int uid){userMapper.updateUser(user,uid);}

    public void updateUserMark(int mark,int uid){userMapper.updateUserMark(mark,uid);}

    public void updateUserState(User user){userMapper.updateUserState(user);}

    public List<User> getUserByResearchInput(String researchInput){return userMapper.getUserByResearchInput(researchInput);}

}
