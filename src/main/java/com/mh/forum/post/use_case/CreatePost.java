package com.mh.forum.post.use_case;

import com.mh.forum.post.model.Post;
import com.mh.forum.post.model.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatePost {

    @Autowired
    PostRepository postRepository;

    public Post execute(Post post, String creator, String idUser) {
        Post postToAdd = new Post(post.getSubject(),
                post.getContent(),
                post.getCategory(),
                creator,
                idUser);
        postToAdd = postRepository.save(postToAdd);
        return postToAdd;
    }

}
