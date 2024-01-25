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
@RequestMapping(value = "")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    // display landing page
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView landingPage(@RequestParam(value = "categoryId", required = false) Long categoryId) {
        List<Item> itemList;
        ModelAndView modelAndView = new ModelAndView();

        if(nonNull(categoryId)) {
            modelAndView.addObject("categoryId", categoryId);
            itemList = itemService.getItemsByCategory(categoryId);
        } else {
            itemList = itemService.getAllItems();
        }

        List<Category> categories = categoryService.getAllCategories();
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("numberOfItems", itemList.size());
        modelAndView.addObject("items", itemList);
        modelAndView.addObject("numberOfCategories", categories.size());
        modelAndView.setViewName("items/homePage");
        return modelAndView;
    }

    @RequestMapping(value = "/item/create", method = RequestMethod.GET)
    public String createItemPage(Model model, Item item) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "items/createItemPage";
    }

    @RequestMapping(value = "/item/create", method = RequestMethod.POST)
    public String createItem(@Valid Item item, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Category> categories = categoryService.getAllCategories();
            model.addAttribute("categories", categories);
            return "items/createItemPage";
        }
        itemService.createItem(item);
        return "redirect:/";
    }

    // display single item
    @RequestMapping(value = "/item/view", method = RequestMethod.GET)
    public ModelAndView viewSingleItem(@RequestParam(value = "id") Long id) {
        Item item = itemService.getOneItem(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("items/viewItemPage");
        modelAndView.addObject("item", item);
        return modelAndView;
    }

    // editing functionality and display editing page
    @RequestMapping(value = "/item/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editSingleItem(@PathVariable(value = "id") Long id) {
        Item item = itemService.getOneItem(id);
        List<Category> categories = categoryService.getAllCategories();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("items/editItemPage");
        modelAndView.addObject("item", item);
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    // update item data and save to Repo
    @RequestMapping(value = "/item/update/{id}", method = RequestMethod.POST)
    public String updateItemData(Item item, @PathVariable(value = "id") Long id) {
        itemService.updateItem(id, item);
        return "redirect:/";
    }

    // delete functionality
    @RequestMapping(value = "/item/delete/{id}", method = RequestMethod.POST)
    public String deleteItemById(@PathVariable("id") Long id) {
        itemService.deleteItemById(id);
        return "redirect:/";
    }
}
