package com.aiolos.comment.service;

import com.aiolos.comment.common.CustomizeException;
import com.aiolos.comment.model.UserModel;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @author Aiolos
 * @date 2019-10-13 11:04
 */
public interface IUserService {

    UserModel getUser(Integer id);

    UserModel register(UserModel userModel) throws CustomizeException, UnsupportedEncodingException, NoSuchAlgorithmException;

    UserModel login(String telphone, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException, CustomizeException;
}
