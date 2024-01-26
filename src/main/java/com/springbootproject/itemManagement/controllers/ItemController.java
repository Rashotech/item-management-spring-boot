package com.springbootproject.itemManagement.controllers;

import com.springbootproject.itemManagement.models.Category;
import com.springbootproject.itemManagement.models.Item;
import com.springbootproject.itemManagement.services.CategoryService;
import com.springbootproject.itemManagement.services.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static java.util.Objects.nonNull;

@Controller
@RequestMapping(value = "/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ModelAndView itemListPage(@RequestParam(value = "categoryId", required = false) Long categoryId) {
        List<Item> itemList;
        ModelAndView modelAndView = new ModelAndView();

        if(nonNull(categoryId)) {
            modelAndView.addObject("categoryId", categoryId);
            itemList = itemService.getItemsByCategory(categoryId);
        } else {
            itemList = itemService.getAllItems();
        }

        List<Category> categories = categoryService.getAllCategories();
        int numberOfItems = itemService.getTotalNumberOfItems();
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("numberOfItems", numberOfItems);
        modelAndView.addObject("items", itemList);
        modelAndView.addObject("numberOfCategories", categories.size());
        modelAndView.setViewName("items/listItemPage");
        return modelAndView;
    }

    @GetMapping(value = "/create")
    public String createItemPage(Model model, Item item) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "items/createItemPage";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createItem(@Valid Item item, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Category> categories = categoryService.getAllCategories();
            model.addAttribute("categories", categories);
            return "items/createItemPage";
        }
        itemService.createItem(item);
        return "redirect:/items";
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView viewSingleItem(@RequestParam(value = "id") Long id) {
        Item item = itemService.getOneItem(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("items/viewItemPage");
        modelAndView.addObject("item", item);
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editSingleItem(@PathVariable(value = "id") Long id) {
        Item item = itemService.getOneItem(id);
        List<Category> categories = categoryService.getAllCategories();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("items/editItemPage");
        modelAndView.addObject("item", item);
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String updateItemData(Item item, @PathVariable(value = "id") Long id) {
        itemService.updateItem(id, item);
        return "redirect:/items";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteItemById(@PathVariable("id") Long id) {
        itemService.deleteItemById(id);
        return "redirect:/items";
    }
}
