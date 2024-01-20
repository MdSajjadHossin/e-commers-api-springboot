package com.springboot.ecommers.service;

import com.springboot.ecommers.entity.User;
import com.springboot.ecommers.exceptions.UserException;

public interface UserService {

    public User findUserById(Long userId) throws UserException;

    public User findUserProfileByJwt(String jwt) throws UserException;

}
