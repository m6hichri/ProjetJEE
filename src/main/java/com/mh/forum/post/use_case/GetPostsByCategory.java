package com.mh.forum.post.use_case;

import com.mh.forum.post.model.Post;
import com.mh.forum.post.model.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@Service
public class GetPostsByCategory {

    @Autowired
    PostRepository postRepository;

    public Iterable<Post> execute(String category) {
        return postRepository.findPostsByCategory(category)
                .collect(Collectors.toList());
    }
}
