package com.mh.forum.post.infrastructure.controller;

import com.mh.forum.comment.dto.AddCommentDto;
import com.mh.forum.configuration.UserConfig;
import com.mh.forum.post.infrastructure.dto.AddPostDto;
import com.mh.forum.post.infrastructure.dto.PostDto;
import com.mh.forum.post.model.Post;
import com.mh.forum.post.use_case.CreerPost;
import com.mh.forum.post.use_case.PostService;
import com.mh.forum.user.dto.UserDto;
import com.mh.forum.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/forum")
public class Postontroller {
    @Autowired
    PostService postService;

    @Autowired
    CreerPost creerPost;

    @Autowired
    UserService userService;

    @Autowired
    UserConfig userConfig;

    @PostMapping("/post")
    public ResponseEntity<PostDto> addPost(@RequestBody AddPostDto addPost, @RequestHeader("Authorization") String token) {
        UserDto userDto = userService.findUserByToken(userConfig.extractToken(token));
        if (null != userDto) {
            return new ResponseEntity<PostDto>(
                    creerPost.execute(
                            new Post(addPost.getSubject(), ...),
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
        if (null != userDto) {
            return new ResponseEntity<PostDto>(
                    postService.addComment(
                            id,
                            addCommentDto,
                            userDto.getEmail(),
                            userDto.getIdUser(),
                            userDto.getLastName()
                    ),
                    HttpStatus.OK
            );
        }
        return new ResponseEntity<PostDto>((PostDto) null, HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable String id) {
        return new ResponseEntity<PostDto>(postService.getPost(id), HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<Iterable<PostDto>> getPosts() {
        return new ResponseEntity<Iterable<PostDto>>(postService.getPosts(), HttpStatus.OK);
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<PostDto> deletePost(@PathVariable String id, @RequestHeader("Authorization") String token) {
        UserDto userDto = userService.findUserByToken(userConfig.extractToken(token));
        if (null != userDto) {
            return new ResponseEntity<PostDto>(postService.deletePost(id), HttpStatus.OK);
        }
        return new ResponseEntity<PostDto>((PostDto) null, HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/post/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody AddPostDto postUpdateDto, @PathVariable String id, @RequestHeader("Authorization") String token) {
        UserDto userDto = userService.findUserByToken(userConfig.extractToken(token));
        if (null != userDto) {
            return new ResponseEntity<PostDto>(postService.updatePost(postUpdateDto, id), HttpStatus.OK);
        }
        return new ResponseEntity<PostDto>((PostDto) null, HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/post/{idPost}/like")
    public ResponseEntity<Boolean> addLike(@PathVariable String idPost, @RequestHeader("Authorization") String token) {
        UserDto userDto = userService.findUserByToken(userConfig.extractToken(token));
        if (null != userDto) {
            return new ResponseEntity<Boolean>(postService.addLike(idPost, userDto.getIdUser()), HttpStatus.OK);
        }
        return new ResponseEntity<Boolean>(false, HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/post/{id}/dislike")
    public ResponseEntity<Boolean> dislike(@PathVariable String id, @RequestHeader("Authorization") String token) {
        UserDto userDto = userService.findUserByToken(userConfig.extractToken(token));
        if (null != userDto) {
            return new ResponseEntity<Boolean>(postService.dislike(id), HttpStatus.OK);
        }
        return new ResponseEntity<Boolean>(false, HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/posts/creator")
    public ResponseEntity<Iterable<PostDto>> findPostsByCreator(@RequestHeader("Authorization") String token) {
        UserDto userDto = userService.findUserByToken(userConfig.extractToken(token));
        if (null != userDto) {
            return new ResponseEntity<Iterable<PostDto>>(postService.getPostsByUser(userDto.getIdUser()), HttpStatus.OK);
        }
        return new ResponseEntity<Iterable<PostDto>>((Iterable<PostDto>) null, HttpStatus.FORBIDDEN);
    }

    @GetMapping("/posts/category/{category}")
    public ResponseEntity<Iterable<PostDto>> findPostsByCategory(@PathVariable String category) {
        return new ResponseEntity<Iterable<PostDto>>(postService.getPostsByCategory(category), HttpStatus.OK);
    }


    @GetMapping("/post/{id}/likes")
    public ResponseEntity<Integer> getLikesByPost(@PathVariable String id) {
        return new ResponseEntity<Integer>(postService.getLikesByPost(id), HttpStatus.OK);
    }

}
