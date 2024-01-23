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
@RequestMapping(value = "")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    // display landing page
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView landingPage() {
        ModelAndView modelAndView = new ModelAndView();
        List<Item> itemList = itemService.getAllItems();
        int numberOfItems = itemService.getTotalNumberOfItems();
        modelAndView.addObject("numberOfItems", numberOfItems);
        modelAndView.addObject("items", itemList);
        modelAndView.setViewName("items/homePage");
        return modelAndView;
    }

    // Display Item Create page
    @RequestMapping(value = "/item/create", method = RequestMethod.GET)
    public ModelAndView registerItemPage() {
        List<Category> categories = categoryService.getAllCategories();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("items/createItemPage");
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    // registered items
    @RequestMapping(value = "/item/create", method = RequestMethod.POST)
    public ModelAndView register(
            @RequestParam(value = "itemName") String name,
            @RequestParam(value = "itemQuantity") Integer quantity,
            @RequestParam(value = "itemCategory") Category category
    ) {
        itemService.createItem(name, quantity, category);
        String redirectUrl = "/";
        return new ModelAndView(new RedirectView(redirectUrl));
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
    @RequestMapping(value = "/item/delete/{id}", method = RequestMethod.GET)
    public String deleteItemById(@PathVariable("id") Long id) {
        itemService.deleteItemById(id);
        return "redirect:/";
    }
}
