package com.springbootproject.itemManagement.services;

import com.springbootproject.itemManagement.entity.Item;
import com.springbootproject.itemManagement.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;

    // create item and save into Repository connected to the database
    @Override
    public void createItem(String name, double price, String description) {
        Item item = new Item();
        item.setName(name);
        item.setPrice(price);
        item.setDescription(description);
        itemRepository.save(item);
    }

    // get all items from repository using in-built findAll() method.
    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    // get a single item using item ID from repository
    @Override
    public Item getOneItem(Long id) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        return optionalItem.orElse(null);
    }

    // update item data using inbuilt save() method
    @Override
    public void updateItem(Item item) {
        itemRepository.save(item);
    }

    // delete item from repository using inbuilt deleteById() method by passing item ID
    @Override
    public void deleteItemById(Long id) {
        itemRepository.deleteById(id);
    }
}
