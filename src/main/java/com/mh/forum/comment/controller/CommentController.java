package com.mh.forum.comment.controller;
import com.mh.forum.comment.dto.CommentDto;
import com.mh.forum.comment.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/forum")
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("/post/{id}/comments")
    public Iterable<CommentDto> getCommentsByPost(@PathVariable String id) {
        return commentService.getCommentsByPost(id);
    }

    @GetMapping("/comments/creator/{creator}")
    public Iterable<CommentDto> getCommentsByCreator(@PathVariable String creator) {
        return commentService.getCommentsByUser(creator);
    }


}
