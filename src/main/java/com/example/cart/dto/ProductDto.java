package com.example.cart.dto;

import com.example.cart.document.Product;

public class ProductDto {
    private int id;
    private Product products;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProducts() {
        return products;
    }

    public void setProducts(Product products) {
        this.products = products;
    }
}
