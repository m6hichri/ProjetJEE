package com.mh.forum.post.infrastructure.dto;

import lombok.Getter;

//@Builder
@Getter
public class AddPostDto {
     String subject;
     String content;
     String category;
     String name;

     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
     }

  /*   public String getSubject(){
          return subject;
     }
     public String getContent(){
          return content;
     }*/
}
