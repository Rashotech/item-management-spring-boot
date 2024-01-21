package com.springbootproject.itemManagement.controllers;

import com.springbootproject.itemManagement.models.Item;
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
        List<Item> itemList = itemService.getAllItems();
        modelAndView.addObject("items", itemList);
        modelAndView.setViewName("items/homePage");
        return modelAndView;
    }

    // Display Item Create page
    @RequestMapping(value = "/item/create", method = RequestMethod.GET)
    public ModelAndView registerItemPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("items/createItemPage");
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
    @RequestMapping(value = "/item/create", method = RequestMethod.POST)
    public ModelAndView register(
            @RequestParam(value = "itemName") String name,
            @RequestParam(value = "itemQuantity") Integer quantity,
            @RequestParam(value = "itemCategory") String category
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
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("items/editItemPage");
        modelAndView.addObject("item", item);
        return modelAndView;
    }

    // update item data and save to Repo
    @RequestMapping(value = "/item/update/{id}", method = RequestMethod.POST)
    public ModelAndView updateItemData(Item item, @PathVariable(value = "id") Long id) {
        item.setId(id);
        itemService.updateItem(item);
        String redirectUrl = "/";
        return new ModelAndView(new RedirectView(redirectUrl));
    }


    // delete functionality
    @RequestMapping(value = "/item/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteItemById(@PathVariable("id") Long id) {
        Item itemToDelete= itemService.getOneItem(id);
        ModelAndView modelAndView = new ModelAndView();
        if (itemToDelete != null) {
            itemService.deleteItemById(id);
            List<Item> itemList = itemService.getAllItems();
            modelAndView.addObject("items", itemList);
            String redirectUrl = "/";
            return new ModelAndView(new RedirectView(redirectUrl));
        }
        return modelAndView;
    }
}
