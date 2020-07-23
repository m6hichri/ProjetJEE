package com.mh.forum.user.dao;


import com.mh.forum.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoUserRepository extends MongoRepository<User,String> {

    User findUserByEmail(String email);
    User findUserByIdUser(String idUser);
    User findUserByToken(String token);

}
