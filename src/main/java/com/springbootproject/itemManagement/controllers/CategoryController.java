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
import java.util.Optional;


@Controller
@RequestMapping(value = "/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView listCategoriesPage() {
        ModelAndView modelAndView = new ModelAndView();
        List<Category> categories = categoryService.getAllCategories();
        modelAndView.addObject("categories", categories);
        modelAndView.setViewName("categories/listCategories");
        return modelAndView;
    }

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

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editCategory(@PathVariable(value = "id") Long id) {
        Optional<Category> category = categoryService.getOneCategory(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("categories/editCategory");
        modelAndView.addObject("category", category);
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String updateItemData(Category category, @PathVariable(value = "id") Long id) {
        categoryService.updateCategory(id, category);
        return "redirect:/categories";
    }
}
