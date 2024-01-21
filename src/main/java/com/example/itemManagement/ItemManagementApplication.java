package com.example.itemManagement;

import com.example.itemManagement.entity.Item;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ItemManagementApplication {

    public static void main(String[] args) {

        SpringApplication.run(ItemManagementApplication.class, args);
        System.out.println("Hello World!");
    }
}
