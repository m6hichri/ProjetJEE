package com.mh.forum.post.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mh.forum.comment.dto.CommentDto;
import com.mh.forum.like.dto.LikeDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class PostDto {
    String idPost;
    String idUser;
    String subject;
    String content;
    String creator;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime dateCreate = LocalDateTime.now();
    Integer likesCount;
    @Singular
    List<CommentDto> comments;
    List<LikeDto> likes;
    String category;
    double collectes;
}
