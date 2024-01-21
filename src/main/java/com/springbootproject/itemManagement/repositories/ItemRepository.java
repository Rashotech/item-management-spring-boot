package com.springbootproject.itemManagement.repositories;

import com.springbootproject.itemManagement.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
