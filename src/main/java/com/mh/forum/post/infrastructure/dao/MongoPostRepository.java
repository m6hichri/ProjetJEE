package com.mh.forum.post.infrastructure.dao;


import com.mh.forum.post.adaptateur.PostAdaptateur;
import com.mh.forum.post.model.Post;
import com.mh.forum.post.model.PostRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class MongoPostRepository implements PostRepository {

    private final MongoTemplate mongoTemplate;

    public MongoPostRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Post save(Post post) {
        MongoPost mp = mongoTemplate.save(PostAdaptateur.postToMongoPostBDT(post));
        Post newPost = PostAdaptateur.mongoPostToPost(mp);
        return newPost;
    }

    @Override
    public Optional<Post> findById(String id) {
        return Optional.empty();
    }

    @Override
    public Stream<Post> findAllByOrderByDateCreateDesc() {
        return null;
    }

    @Override
    public Stream<Post> findPostsByIdUser(String idUser) {
        return null;
    }

    @Override
    public Stream<Post> findPostsByCategory(String category) {
        return null;
    }

    @Override
    public void delete(Post post) {

    }
}
