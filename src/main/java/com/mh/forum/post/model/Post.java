package com.mh.forum.post.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.mh.forum.comment.model.Comment;
import com.mh.forum.like.model.Like;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    String idUser;
    @Setter
    String subject;
    @Setter
    String content;
    @Setter
    String creator;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime dateCreate;
    int likesCount;
    Set<Comment> comments;
    //Set<Like> likes;
    List<Like> likes;
    @Setter
    String category;
    @Setter
    double collectes;

    public Post(String subject, String content, String category, String creator, String idUser) {
        this.idUser = idUser;
        this.subject = subject;
        this.content = content;
        this.creator = creator;
        this.dateCreate = LocalDateTime.now();
        comments = new HashSet<Comment>(0);
        likes = new ArrayList<Like>();
        //likes = new HashSet<Like>(0);
        this.category = category;
        collectes = 0.0;

    }



    public double addCollect(double collectes) {

        return this.collectes += collectes;
    }
    public void addLike() {
        likesCount++;
    }

    public void dislike() {
        likesCount--;
    }

    public boolean addComment(Comment comment) {

        return comments.add(comment);
    }

    public boolean addLikes(Like like) {
        return likes.add(like);
    }
}
