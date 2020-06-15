package com.mh.forum.comment.services;

import com.mh.forum.comment.dto.AddCommentDto;
import com.mh.forum.comment.dto.CommentDto;
import com.mh.forum.post.dto.PostDto;

import java.util.List;


public interface CommentService {


    //PostDto addComment(String id, AddCommentDto addCommentDto, String creator);

    Iterable<CommentDto> getCommentsByPost(String id);

    Iterable<CommentDto> getCommentsByUser(String userEmail);

//    int getLikesByPost(String id);
//
//
//    boolean addLike(String id);


}
