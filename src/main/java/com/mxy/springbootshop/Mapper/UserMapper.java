package com.mxy.springbootshop.Mapper;

import org.apache.ibatis.annotations.Mapper;
import com.mxy.springbootshop.POJO.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

     int insertUser(User user);

     User getUserByUsername(String username);

     User userLogin(@Param("username") String username,@Param("password") String password);

     User getUserByUid(int uid);

     List<User> getAllUsers();

     void deleteUserByUid(int uid);

    void updateUser(@Param("user") User user,@Param("uid") int uid);

    void updateUserMark(@Param("mark") int mark,@Param("uid") int uid);

    void updateUserState(User user);

    List<User> getUserByResearchInput(String researchInput);
}
