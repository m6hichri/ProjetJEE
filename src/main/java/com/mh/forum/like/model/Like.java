package com.mh.forum.like.model;


import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@NoArgsConstructor
@ToString
public class Like {
    @Id
    String idLike;
    String idUser;
    //User user;

//    public Like(User user) {
//        this.user = user;
//    }

    public Like(String  idUser) {
        this.idUser = idUser;
    }

}
