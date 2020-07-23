package com.mh.forum.comment.services;

import com.mh.forum.comment.dao.MongoCommentRepositry;
import com.mh.forum.comment.dto.CommentDto;
import com.mh.forum.comment.model.Comment;
import com.mh.forum.exceptions.PostNotFoundException;
import com.mh.forum.post.dao.MongoPostRepository;
import com.mh.forum.post.dto.PostDto;
import com.mh.forum.post.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

//import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    MongoPostRepository mongoPostRepository;
    @Autowired
    MongoCommentRepositry mongoCommentRepositry;

    @Override
    public Iterable<CommentDto> getCommentsByPost(String id) {
        Post post = mongoPostRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        Set<Comment> comments = post.getComments();
        return comments.stream().map(this::convertToCommentDto).collect(Collectors.toList());
    }

    @Override
    public Iterable<CommentDto> getCommentsByUser(String idUser) {
        return mongoCommentRepositry.findCommentByIdUser(idUser)
                .map(this::convertToCommentDto)
                .collect(Collectors.toList());
    }

    private CommentDto convertToCommentDto(Comment comment) {
        return CommentDto.builder().idUser(comment.getIdUser()).content(comment.getContent()).owner(comment.getOwner())
                .dateCreate(comment.getDateCreate()).build();
    }

    private PostDto convertToPostDto(Post post) {
        return PostDto.builder()
                .idPost(post.getIdPost())
                .idUser(post.getIdUser())
                .subject(post.getSubject())
                .dateCreate(post.getDateCreate())
                .content(post.getContent())
                .likesCount(post.getLikesCount())
                .comments(post.getComments().stream().map(this::convertToCommentDto).collect(Collectors.toList()))
                .category(post.getCategory())
                .build();
    }

}
