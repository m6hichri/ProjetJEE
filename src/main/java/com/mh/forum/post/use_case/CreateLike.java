package com.mh.forum.post.use_case;

import com.mh.forum.like.model.Like;
import com.mh.forum.post.model.Post;
import com.mh.forum.post.model.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CreateLike {


    @Autowired
    PostRepository postRepository;


    public boolean execute(String idPost, String idUser) {
        Post post = postRepository.findById(idPost).orElse(null);
        List<Like> likes = post.getLikes();
        if (!likes.isEmpty()) {
            for (int i = 0; i < likes.size(); i++) {
                if (likes.get(i).getIdUser().equals(idUser)) {
                    return false;
                }
            }
        }
        Like like = new Like(idUser);
        post.addLike();// increment likesCount
        post.addLikes(like);// add like to this post
        postRepository.save(post);//save post
        return true;
    }
}