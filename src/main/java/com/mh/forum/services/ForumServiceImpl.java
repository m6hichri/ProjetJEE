package com.mh.forum.services;

import com.mh.forum.dao.CommentRepositry;
import com.mh.forum.dao.ForumRepository;
import com.mh.forum.dto.AddCommentDto;
import com.mh.forum.dto.AddPostDto;
import com.mh.forum.dto.CommentDto;
import com.mh.forum.dto.PostDto;
import com.mh.forum.entity.Comment;
import com.mh.forum.entity.Post;
import com.mh.forum.exceptions.PostNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ForumServiceImpl implements ForumService {

    @Autowired
    ForumRepository forumRepository;
    CommentRepositry commentRepositry;

    @Override
    public PostDto addPost(AddPostDto addPostDto, String creator) {

        Post post = new Post(creator, addPostDto.getSubject(), addPostDto.getContent());
        post = forumRepository.save(post);
        return convertToPostDto(post);
    }

    @Override
    public PostDto addComment(String id, AddCommentDto addCommentDto, String creator) {
        Post post = forumRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        Comment comment = new Comment(creator, addCommentDto.getContent());
        post.addComment(comment);
        forumRepository.save(post);
        return convertToPostDto(post);
    }

    @Override
    public PostDto getPost(String id) {
        Post post = forumRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        return convertToPostDto(post);
    }

    @Override
    public Iterable<PostDto> getPostsByUser(String creator) {
        return forumRepository.findPostsByUserEmail(creator)
                .map(this::convertToPostDto)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<PostDto> getPosts() {
        return forumRepository.findAllByOrderByDateCreate();
    }

    @Override
    public Iterable<CommentDto> getCommentsByPost(String id) {
        Post post = forumRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        Set<Comment> comments = post.getComments();
        return comments.stream().map(this::convertToCommentDto).collect(Collectors.toList());
    }

    @Override
    public Iterable<CommentDto> getCommentsByUser(String creator) {
        return commentRepositry.findCommentByUserEmail(creator)
                .map(this::convertToCommentDto)
                .collect(Collectors.toList());
    }

    @Override
    public PostDto deletePost(String id) {
        Post post = forumRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        forumRepository.delete(post);
        return convertToPostDto(post);
    }

    @Override
    public PostDto updatePost(AddPostDto updatePostDto, String id) {
        Post post = forumRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        String content = updatePostDto.getContent();

        if (null != content) {
            post.setContent(content);
        }
        String subject = updatePostDto.getSubject();
        if (null != content) {
            post.setSubject(subject);
        }
        forumRepository.save(post);
        return convertToPostDto(post);
    }

    @Override
    public boolean addLike(String id) {

        Post post = forumRepository.findById(id).orElse(null);
        if (null != null) {
            post.addLike();
            forumRepository.save(post);
            return true;
        }
        return false;
    }


    private PostDto convertToPostDto(Post post) {
        return PostDto.builder().idPost(post.getIdPost()).userEmail(post.getUserEmail()).subject(post.getSubject())
                .dateCreate(post.getDateCreate()).content(post.getContent())
                .likes(post.getLikes())
                .comments(post.getComments().stream().map(this::convertToCommentDto).collect(Collectors.toList()))
                .build();
    }

    private CommentDto convertToCommentDto(Comment comment) {
        return CommentDto.builder().userEmail(comment.getUserEmail()).content(comment.getContent())
                .dateCreate(comment.getDateCreate()).build();
    }

}
