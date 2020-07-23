package com.mh.forum.post.services;

import com.mh.forum.comment.dto.AddCommentDto;
import com.mh.forum.comment.dto.CommentDto;
import com.mh.forum.post.dto.AddPostDto;
import com.mh.forum.post.dto.PostDto;

public interface PostService {


    PostDto addPost(AddPostDto addPostDto, String creator, String idUser);

    PostDto addComment(String id, AddCommentDto addCommentDto, String creator, String idUser, String owner);

    PostDto addCollectes(String id, double collect);

    PostDto getPost(String id);

    Iterable<PostDto> getPostsByUser(String creator);

    Iterable<PostDto> getPosts();

    Iterable<PostDto> getPostsByCategory(String category);

    Iterable<CommentDto> getCommentsByPost(String id);

    int getLikesByPost(String id);

    PostDto deletePost(String id);

    PostDto updatePost(AddPostDto updatePostDto, String id);

    boolean addLike(String idPost, String idUser);

    boolean dislike(String id);


}
