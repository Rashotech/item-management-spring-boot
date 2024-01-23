package com.springbootproject.itemManagement.controllers;

import com.springbootproject.itemManagement.models.Category;
import com.springbootproject.itemManagement.models.Item;
import com.springbootproject.itemManagement.services.CategoryService;
import com.springbootproject.itemManagement.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;


@Controller
@RequestMapping(value = "/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView createCategoryPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("categories/createCategory");
        return modelAndView;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createCategory(
            @RequestParam(value = "categoryName") String name,
            @RequestParam(value = "categoryDescription") String description
    ) {
        categoryService.createCategory(name, description);
        String redirectUrl = "/";
        return new ModelAndView(new RedirectView(redirectUrl));
    }
}
