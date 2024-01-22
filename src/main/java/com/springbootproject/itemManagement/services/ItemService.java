package com.springbootproject.itemManagement.services;

import com.springbootproject.itemManagement.models.Category;
import com.springbootproject.itemManagement.models.Item;

import java.util.List;

public interface ItemService {
    public void createItem(String name, Integer quantity, Category category);
    List<Item> getAllItems();
    public Item getOneItem(Long id);
    public void updateItem(Long id, Item item);
    public void deleteItemById(Long id);

    public int totalQuantity();
    public int numberOfItems();
}
