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
            categoryRepository.saveAll(List.of(
                    new Category("Electronic"),
                    new Category("Fashion"),
                    new Category("Medical"),
                    new Category("Automobile")
            ));
        };
    }
}
