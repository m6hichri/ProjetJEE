package com.mh.forum.post.model;

import java.util.Optional;
import java.util.stream.Stream;

public interface PostRepository {
    Post save(Post post);
    Optional<Post> findById(String id);
    Stream<Post> findAllByOrderByDateCreateDesc();
    Stream<Post> findPostsByIdUser(String idUser);
    Stream<Post> findPostsByCategory(String category);
    void delete(Post post);
}


