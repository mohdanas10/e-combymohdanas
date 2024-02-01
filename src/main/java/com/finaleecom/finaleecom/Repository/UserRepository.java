package com.finaleecom.finaleecom.Repository;

import com.finaleecom.finaleecom.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User,String > {
    public User findByUserName(String userName);
    public User findByUserPassword(String userPassword);
}
