package com.mh.forum.post.use_case;

import com.mh.forum.exceptions.PostNotFoundException;
import com.mh.forum.post.model.Post;
import com.mh.forum.post.model.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UpdatePost {


    @Autowired
    PostRepository postRepository;

    public Post execute(Post post, String id) {
        Post postToUpdate = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        String content = post.getContent();

        if (null != content) {
            postToUpdate.setContent(content);
        }
        String subject = post.getSubject();
        if (null != content) {
            postToUpdate.setSubject(subject);
        }
        postRepository.save(postToUpdate);
        return postToUpdate;
    }

}
