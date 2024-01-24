package com.springbootproject.itemManagement.services;

import com.springbootproject.itemManagement.models.Category;
import com.springbootproject.itemManagement.models.Item;

import java.util.List;

public interface ItemService {
    public void createItem(Item item);
    List<Item> getAllItems();
    public Item getOneItem(Long id);
    public void updateItem(Long id, Item item);
    public void deleteItemById(Long id);
    public int getTotalNumberOfItems();
    public List<Item> getItemsByCategory(Long id);
}
