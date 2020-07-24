package com.mh.forum.post.use_case;

import com.mh.forum.exceptions.PostNotFoundException;
import com.mh.forum.post.model.Post;
import com.mh.forum.post.model.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GetPost {

    @Autowired
    PostRepository postRepository;

    public Post execute(String id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        return post;
    }
}
