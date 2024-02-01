package com.finaleecom.finaleecom.Services;


import com.finaleecom.finaleecom.Model.User;
import com.finaleecom.finaleecom.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAlLUser(){
        return this.userRepository.findAll();
    }

    public User findUserById(String id ){
        return  this.userRepository.findById(id).get();
    }

    public User findByUserName(String userName){
        return this.userRepository.findByUserName(userName);
    }

    public User findByUserPassword(String userPassword){
        return this.userRepository.findByUserPassword(userPassword);
    }

    public void save (User user ){
        this.userRepository.save(user);
    }

}
