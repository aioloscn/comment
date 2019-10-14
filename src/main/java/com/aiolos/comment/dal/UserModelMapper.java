package com.aiolos.comment.dal;

import com.aiolos.comment.model.UserModel;
import org.apache.ibatis.annotations.Param;

public interface UserModelMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(UserModel record);

    int insertSelective(UserModel record);

    UserModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserModel record);

    int updateByPrimaryKey(UserModel record);

    UserModel selectByTelphoneAndPassword(@Param("telphone") String telphone, @Param("password") String password);
}