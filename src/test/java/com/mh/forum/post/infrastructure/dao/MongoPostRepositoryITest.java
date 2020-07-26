package com.mh.forum.post.infrastructure.dao;

import com.mh.forum.post.model.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class MongoPostRepositoryITest {
    private MongoPostRepository mongoPostRepository;

    @Mock
    private MongoTemplate mongoTemplate;

    @BeforeEach
    void setUp() {
        mongoPostRepository = new MongoPostRepository(mongoTemplate);
    }

    @Nested
    class AddPostShould {

        private Post post;
        private MongoPost mongoPost;

        @BeforeEach
        void setUp() {
            final String subject = "subject";
            final String content = "content";
            final String category = "category";
            final String creator = "creator";
            final String idUser = "idUser";

            System.out.println("Salut");


            post = new Post(subject, content, category, creator, idUser);

            mongoPost = new MongoPost(subject, content, category, creator, idUser, post.getDateCreate());

            when(mongoTemplate.save(any())).thenReturn(mongoPost);
        }

        @Test
        public void return_added_user() {
            // when
            final Post result = mongoPostRepository.save(post);
            // then
            assertThat(result).isEqualToComparingFieldByField(post);
        }

        @Test
        public void call_mongoSave() {
            // when
            mongoPostRepository.save(post);

            // then
            ArgumentCaptor<MongoPost> mongoPostArgumentCaptor = ArgumentCaptor.forClass(MongoPost.class);
            verify(mongoTemplate).save(mongoPostArgumentCaptor.capture());
            assertThat(mongoPostArgumentCaptor.getValue()).isEqualToComparingFieldByField(mongoPost);
        }
    }

}
