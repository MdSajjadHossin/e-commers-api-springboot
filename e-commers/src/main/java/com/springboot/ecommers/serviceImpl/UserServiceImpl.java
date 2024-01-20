package com.springboot.ecommers.serviceImpl;

import com.springboot.ecommers.Repository.UserRepo;
import com.springboot.ecommers.entity.User;
import com.springboot.ecommers.exceptions.UserException;
import com.springboot.ecommers.security.JwtProvider;
import com.springboot.ecommers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JwtProvider jwtProvider;
    @Override
    public User findUserById(Long userId) throws UserException{

        Optional<User> user = userRepo.findById(userId);
        if(user.isPresent()){
            return user.get();
        }
        throw new UserException("User not found with id: "+userId);
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException{

        String email = jwtProvider.getEmailFromToken(jwt);

        User user = userRepo.findByEmail(email);
        if(user == null){
            throw new UserException("User email not found" + email);
        }
        return user;
    }
}
