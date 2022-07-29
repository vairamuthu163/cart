package com.example.cart.service;

import com.example.cart.document.Cart;
import com.example.cart.document.Product;

public interface CartService {
    Cart findById(int id);
    Cart save(Cart cart);
    void findByIdAndAdd(int id, Product product);
    void findByIdAndUpdate(int id, Product product);
}
