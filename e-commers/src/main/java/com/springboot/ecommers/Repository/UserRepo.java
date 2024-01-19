package com.springboot.ecommers.Repository;

import com.springboot.ecommers.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    public User findByEmail(String email);
}
