package com.mh.forum.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Document(collection = "posts")
public class Post {
    @Id
    String idPost;
    String userEmail;
    @Setter
    String subject;
    @Setter
    String content;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime dateCreate;
    int likes;
    Set<Comment> comments;
    User creator;

    public Post(String userEmail, String subject, String content) {
        this.userEmail = userEmail;
        this.subject = subject;
        this.content = content;
        this.dateCreate = LocalDateTime.now();
        comments = new HashSet<Comment>(0);
    }

    public void addLike() {
        likes++;
    }

    public boolean addComment(Comment comment) {

        return comments.add(comment);
    }
}
