package com.mh.forum.post.use_case;

import com.mh.forum.post.model.Post;
import com.mh.forum.post.model.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Createdislike {


    @Autowired
    PostRepository postRepository;


    public boolean execute(String id) {

        Post post = postRepository.findById(id).orElse(null);
        if (null != post) {
            post.dislike();
            postRepository.save(post);
            return true;
        }
        return false;
    }
}
