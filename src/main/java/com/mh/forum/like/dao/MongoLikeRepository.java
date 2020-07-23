package com.mh.forum.like.dao;


import com.mh.forum.like.model.Like;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoLikeRepository extends MongoRepository<Like, String> {

    //Stream<Post> findPostsByUserEmail(String userEmail);
    //Like findLikeByUser(User user);
}
