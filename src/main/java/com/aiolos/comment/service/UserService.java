package com.aiolos.comment.service;

import com.aiolos.comment.common.CommonResponse;
import com.aiolos.comment.common.CustomizeException;
import com.aiolos.comment.model.ConcernRelationModel;
import com.aiolos.comment.model.UserModel;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @author Aiolos
 * @date 2019-10-13 11:04
 */
public interface UserService {

    UserModel getUser(Integer id);

    CommonResponse register(UserModel userModel) throws CustomizeException, UnsupportedEncodingException, NoSuchAlgorithmException;

    UserModel login(String telphone, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException, CustomizeException;

    UserModel loginOrRegister(String telphone) throws CustomizeException;

    CommonResponse follow(ConcernRelationModel concernRelationModel) throws CustomizeException;

    CommonResponse cancelFollow(ConcernRelationModel concernRelationModel) throws CustomizeException;
}
