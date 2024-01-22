package com.springbootproject.itemManagement;

import com.springbootproject.itemManagement.models.Category;
import com.springbootproject.itemManagement.repositories.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ItemManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItemManagementApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CategoryRepository categoryRepository) {
        return args -> {
            seedCategoryData(categoryRepository);
        };
    }

    void seedCategoryData(CategoryRepository categoryRepository) {
        // Check if categories already exist
        if (categoryRepository.count() == 0) {
            // Save new categories only if none exist
            categoryRepository.saveAll(List.of(
                    new Category("Electronic", "This is Electronic Category"),
                    new Category("Fashion", "This is Fashion Category"),
                    new Category("Medical", "This is Medical Category"),
                    new Category("Automobile", "This is Automobile Category")
            ));
        } else {
            System.out.println("Categories already exist. Skipping initialization.");
        }
    }
}
