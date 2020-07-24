package com.mh.forum.post.infrastructure.controller;

import com.mh.forum.comment.dto.AddCommentDto;
import com.mh.forum.comment.model.Comment;
import com.mh.forum.configuration.UserConfig;
import com.mh.forum.post.infrastructure.dto.AddPostDto;
import com.mh.forum.post.infrastructure.dto.PostDto;
import com.mh.forum.post.model.Post;
import com.mh.forum.post.use_case.*;
import com.mh.forum.user.dto.UserDto;
import com.mh.forum.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/forum")
public class Postontroller {
//    @Autowired
//    PostService postService;

    @Autowired
    CreatePost createPost;

    @Autowired
    CreateComment createComment;

    @Autowired
    CreateLike createLike;

    @Autowired
    Createdislike createdisLike;

    @Autowired
    DeletePost deletePost;

    @Autowired
    GetLikesByPost getLikesByPost;

    @Autowired
    GetPost getPost;

    @Autowired
    GetPosts getPosts;

    @Autowired

    GetPostsByCategory getPostsByCategory;

    @Autowired
    GetPostsByUser getPostsByUser;

    @Autowired
    UpdatePost updatePost;

    @Autowired
    UserService userService;

    @Autowired
    UserConfig userConfig;

    @PostMapping("/post")
    public ResponseEntity<PostDto> addPost(@RequestBody AddPostDto addPost, @RequestHeader("Authorization") String token) {
        UserDto userDto = userService.findUserByToken(userConfig.extractToken(token));
        Post post = new Post(addPost.getSubject(), addPost.getContent(), addPost.getCategory(), userDto.getEmail(), userDto.getIdUser());
        if (null != userDto) {
            return new ResponseEntity<PostDto>(
                    (MultiValueMap<String, String>) createPost.execute(
                            post,
                            userDto.getEmail(),
                            userDto.getIdUser()
                    ),
                    HttpStatus.OK
            );
        }
        return new ResponseEntity<PostDto>((PostDto) null, HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/post/{id}/comment")
    public ResponseEntity<PostDto> addComment(@PathVariable String id, @RequestBody AddCommentDto addCommentDto, @RequestHeader("Authorization") String token) {
        UserDto userDto = userService.findUserByToken(userConfig.extractToken(token));
        Comment comment = new Comment(userDto.getIdUser(), userDto.getEmail(), addCommentDto.getContent(), userDto.getFirstName());
        if (null != userDto) {
            return new ResponseEntity<PostDto>(
                    (MultiValueMap<String, String>) createComment.execute(
                            id,
                            comment,
                            userDto.getEmail(),
                            userDto.getIdUser(),
                            userDto.getLastName()
                    ),
                    HttpStatus.OK
            );
        }
        return new ResponseEntity<PostDto>((PostDto) null, HttpStatus.UNAUTHORIZED);
    }

//    @PutMapping("/post/{id}/comment")
//    public ResponseEntity<PostDto> addComment(@PathVariable String id, @RequestBody AddCommentDto addCommentDto, @RequestHeader("Authorization") String token) {
//        UserDto userDto = userService.findUserByToken(userConfig.extractToken(token));
//        if (null != userDto) {
//            return new ResponseEntity<PostDto>(
//                    postService.addComment(
//                            id,
//                            addCommentDto,
//                            userDto.getEmail(),
//                            userDto.getIdUser(),
//                            userDto.getLastName()
//                    ),
//                    HttpStatus.OK
//            );
//        }
//        return new ResponseEntity<PostDto>((PostDto) null, HttpStatus.UNAUTHORIZED);
//    }

    @GetMapping("/post/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable String id) {
        return new ResponseEntity<PostDto>((MultiValueMap<String, String>) getPost.execute(id), HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<Iterable<PostDto>> getPosts() {
        return new ResponseEntity<Iterable<PostDto>>((MultiValueMap<String, String>) getPosts.execute(), HttpStatus.OK);
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<PostDto> deletePost(@PathVariable String id, @RequestHeader("Authorization") String token) {
        UserDto userDto = userService.findUserByToken(userConfig.extractToken(token));
        if (null != userDto) {
            return new ResponseEntity<PostDto>((MultiValueMap<String, String>) deletePost.execute(id), HttpStatus.OK);
        }
        return new ResponseEntity<PostDto>((PostDto) null, HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/post/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody AddPostDto postUpdateDto, @PathVariable String id, @RequestHeader("Authorization") String token) {
        UserDto userDto = userService.findUserByToken(userConfig.extractToken(token));
        Post post = new Post(postUpdateDto.getSubject(), postUpdateDto.getContent(), postUpdateDto.getCategory(), userDto.getEmail(), userDto.getIdUser());
        if (null != userDto) {
            return new ResponseEntity<PostDto>((MultiValueMap<String, String>) updatePost.execute(post, id), HttpStatus.OK);
        }
        return new ResponseEntity<PostDto>((PostDto) null, HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/post/{idPost}/like")
    public ResponseEntity<Boolean> addLike(@PathVariable String idPost, @RequestHeader("Authorization") String token) {
        UserDto userDto = userService.findUserByToken(userConfig.extractToken(token));
        if (null != userDto) {
            return new ResponseEntity<Boolean>(createLike.execute(idPost, userDto.getIdUser()), HttpStatus.OK);
        }
        return new ResponseEntity<Boolean>(false, HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/post/{id}/dislike")
    public ResponseEntity<Boolean> dislike(@PathVariable String id, @RequestHeader("Authorization") String token) {
        UserDto userDto = userService.findUserByToken(userConfig.extractToken(token));
        if (null != userDto) {
            return new ResponseEntity<Boolean>(createdisLike.execute(id), HttpStatus.OK);
        }
        return new ResponseEntity<Boolean>(false, HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/posts/creator")
    public ResponseEntity<Iterable<PostDto>> findPostsByCreator(@RequestHeader("Authorization") String token) {
        UserDto userDto = userService.findUserByToken(userConfig.extractToken(token));
        if (null != userDto) {
            return new ResponseEntity<Iterable<PostDto>>((MultiValueMap<String, String>) getPostsByUser.execute(userDto.getIdUser()), HttpStatus.OK);
        }
        return new ResponseEntity<Iterable<PostDto>>((Iterable<PostDto>) null, HttpStatus.FORBIDDEN);
    }

    @GetMapping("/posts/category/{category}")
    public ResponseEntity<Iterable<PostDto>> findPostsByCategory(@PathVariable String category) {
        return new ResponseEntity<Iterable<PostDto>>((MultiValueMap<String, String>) getPostsByCategory.execute(category), HttpStatus.OK);
    }


    @GetMapping("/post/{id}/likes")
    public ResponseEntity<Integer> getLikesByPost(@PathVariable String id) {
        return new ResponseEntity<Integer>(getLikesByPost.execute(id), HttpStatus.OK);
    }

}
