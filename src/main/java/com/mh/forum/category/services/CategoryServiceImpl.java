package com.mh.forum.category.services;

import com.mh.forum.category.dao.MongoCategoryRepository;
import com.mh.forum.category.model.Category;
import com.mh.forum.comment.dao.MongoCommentRepositry;
import com.mh.forum.post.dao.MongoPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    MongoPostRepository mongoPostRepository;
    @Autowired
    MongoCommentRepositry mongoCommentRepositry;
    @Autowired
    MongoCategoryRepository mongoCategoryRepository;

    @Override
    public List<Category> getCategories() {
        return mongoCategoryRepository.findAll();
    }


}
