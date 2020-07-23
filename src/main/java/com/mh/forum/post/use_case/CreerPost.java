package com.mh.forum.post.use_case;

import com.mh.forum.post.model.Post;
import com.mh.forum.post.model.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreerPost {

    @Autowired
    PostRepository postRepository;

    public Post execute(Post addPostDto, String creator, String idUser) {
        Post post = new Post(addPostDto.getSubject(),
                addPostDto.getContent(),
                addPostDto.getCategory(),
                creator,
                idUser);
        post = postRepository.save(post);
        return null;
    }

}
