package com.mh.forum.post.infrastructure.dao;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "post")
public class MongoPost {
    private final String subject;
    private final String content;
    private final String category;
    private final String creator;
    private final String idUser;

    public MongoPost(String subject, String content, String category, String creator, String idUser) {
        this.subject = subject;
        this.content = content;
        this.category = category;
        this.creator = creator;
        this.idUser = idUser;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public String getCategory() {
        return category;
    }

    public String getCreator() {
        return creator;
    }

    public String getIdUser() {
        return idUser;
    }
}
