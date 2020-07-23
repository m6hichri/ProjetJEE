package com.mh.forum.post.infrastructure.dao;


import com.mh.forum.post.model.Post;
import com.mh.forum.post.model.PostRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface MongoPostRepository extends MongoRepository<Post,String>, PostRepository {



    Stream<Post> findAllByOrderByDateCreateDesc();
    Stream<Post> findPostsByIdUser(String idUser);
    Stream<Post> findPostsByCategory(String category);

}
