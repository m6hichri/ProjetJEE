package com.mh.forum.post.use_case;

import com.mh.forum.post.model.Post;
import com.mh.forum.post.model.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@Service
public class GetPostsByUser {

    @Autowired
    PostRepository postRepository;

    public Iterable<Post> execute(String creator) {
        return postRepository.findPostsByIdUser(creator)
                .collect(Collectors.toList());
    }
}
