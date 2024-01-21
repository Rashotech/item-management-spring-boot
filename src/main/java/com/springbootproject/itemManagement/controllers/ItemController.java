package com.springbootproject.itemManagement.controllers;

import com.springbootproject.itemManagement.entity.Item;
import com.springbootproject.itemManagement.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;


@Controller
@RequestMapping(value = "")
public class ItemController {
    @Autowired
    private ItemService itemService;

    // display landing page
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView landingPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("items/landingPage");
        return modelAndView;
    }

    // display home page
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView homePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("items/homePage");
        return modelAndView;
    }

    // display registration page
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView registerItemPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("items/registerItemPage");
        return modelAndView;
    }

    // display inventory page
    @RequestMapping(value = "/inventory", method = RequestMethod.GET)
    public ModelAndView inventoryListPage() {
        ModelAndView modelAndView = new ModelAndView();
        List<Item> itemList = itemService.getAllItems();
        modelAndView.addObject("item", itemList);
        modelAndView.setViewName("items/inventoryListPage");
        return modelAndView;
    }

    // registered items
    @RequestMapping(value = "/registered", method = RequestMethod.POST)
    public ModelAndView register(
            @RequestParam(value = "itemName") String name,
            @RequestParam(value = "itemPrice") double price,
            @RequestParam(value = "itemDesc") String description
    ) {
        itemService.createItem(name, price, description);
        String redirectUrl = "/inventory";
        return new ModelAndView(new RedirectView(redirectUrl));
    }

    // display single item
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView viewSingleItem(@RequestParam(value = "id") Long id) {
        Item item = itemService.getOneItem(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("items/viewItemPage");
        modelAndView.addObject("item", item);
        return modelAndView;
    }

    // editing functionality and display editing page
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editSingleItem(@PathVariable(value = "id") Long id) {
        Item item = itemService.getOneItem(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("items/editItemPage");
        modelAndView.addObject("item", item);
        return modelAndView;
    }

    // update item data and save to Repo
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public ModelAndView updateItemData(Item item, @PathVariable(value = "id") Long id) {
        item.setId(id);
        itemService.updateItem(item);
        String redirectUrl = "/inventory";
        return new ModelAndView(new RedirectView(redirectUrl));
    }


    // delete functionality
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteItemById(@PathVariable("id") Long id) {
        Item itemToDelete= itemService.getOneItem(id);
        ModelAndView modelAndView = new ModelAndView();
        if (itemToDelete != null) {
            itemService.deleteItemById(id);
            List<Item> itemList = itemService.getAllItems();
            modelAndView.addObject("item", itemList);
            String redirectUrl = "/inventory";
            return new ModelAndView(new RedirectView(redirectUrl));
        }
        return modelAndView;
    }
}
