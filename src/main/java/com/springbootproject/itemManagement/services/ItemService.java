package com.springbootproject.itemManagement.services;

import com.springbootproject.itemManagement.entity.Item;

import java.util.List;

public interface ItemService {
    public void createItem(String name, Integer quantity, String category);
    List<Item> getAllItems();
    public Item getOneItem(Long id);
    public void updateItem(Item item);
    public void deleteItemById(Long id);
}
