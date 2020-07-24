package com.mh.forum.post.use_case;

import com.mh.forum.comment.dto.AddCommentDto;
import com.mh.forum.comment.model.Comment;
import com.mh.forum.exceptions.PostNotFoundException;
import com.mh.forum.post.model.Post;
import com.mh.forum.post.model.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateComment {

    @Autowired
    PostRepository postRepository;

    public Post execute(String id, Comment comment, String creator, String idUser, String owner) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        Comment commentToAdd = new Comment(idUser, creator, comment.getContent(), owner);
        System.out.println("*************commentToAdd in service ***********" + commentToAdd);
        post.addComment(commentToAdd);
        post = postRepository.save(post);
        return post;
    }
}
