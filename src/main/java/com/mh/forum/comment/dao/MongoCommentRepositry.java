package com.mh.forum.comment.dao;


import com.mh.forum.comment.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.stream.Stream;

public interface MongoCommentRepositry extends MongoRepository<Comment, String> {

    //Stream<Comment> findCommentByUserEmail(String user);
    Stream<Comment> findCommentByIdUser(String user);
}
