package com.springbootproject.itemManagement.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Item name required")
    private String name;

    @Min(value = 1, message = "Minimum Quantity is 1")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "category", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Category category;

    public Item() {
    }

    public Item(String name, Integer quantity, Category category) {
        this.name = name;
        this.quantity = quantity;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", category=" + category +
                '}';
    }
}
