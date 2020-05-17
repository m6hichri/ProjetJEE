package com.mh.forum.category.controller;


import com.mh.forum.category.model.Category;
import com.mh.forum.category.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/forum")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @CrossOrigin
    @GetMapping("/categories")
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

 /*  @PostMapping("/comment/{creator}")
    //@PreAuthorize("#author==authentication.name")
    public CommentDto addC(@RequestBody AddCommentDto addC, @PathVariable("creator") String author) {
        return forumService.addC(addC, author);
    }*/
}
