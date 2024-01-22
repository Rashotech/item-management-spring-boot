package com.springbootproject.itemManagement.repositories;

import com.springbootproject.itemManagement.models.Category;
import com.springbootproject.itemManagement.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findItemsByCategory(Category category);
}
