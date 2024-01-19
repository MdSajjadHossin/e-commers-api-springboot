package com.springboot.ecommers.service;

import com.springboot.ecommers.entity.User;

public interface UserService {

    public User findUserById(Long userId);

    public User findUserProfileByJwt(String jwt);

}
