package com.mh.forum.post.adaptateur;

import com.mh.forum.comment.model.Comment;
import com.mh.forum.post.infrastructure.dao.MongoPost;
import com.mh.forum.post.model.Post;
import com.mongodb.Mongo;

import java.util.ArrayList;
import java.util.Set;

public class PostAdaptateur {

    public static Post mongoPostToPost(MongoPost mongoPost){
        Post post = new Post(mongoPost.getSubject(), mongoPost.getContent(), mongoPost.getCategory(), mongoPost.getCreator(), mongoPost.getIdUser(), mongoPost.getDateCreate());

        return post;
    }

    public static MongoPost postToMongoPostBDT(Post post){
        MongoPost mongoPost = new MongoPost(post.getSubject(), post.getContent(), post.getCategory(), post.getCreator(), post.getIdUser(), post.getDateCreate());
        return mongoPost;
    }
}
