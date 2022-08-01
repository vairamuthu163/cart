package com.example.cart.service;

import com.example.cart.document.Cart;
import com.example.cart.document.Product;
import com.mongodb.client.result.UpdateResult;

import java.util.List;

public interface CartService {
    Cart findById(int id);
    Cart save(Cart cart);
    UpdateResult findByIdAndAdd(int id, Product product);
    Cart findByIdAndUpdate(int id, Product product);
    void removeFromCart(int userId,int id);
    List<Cart> findAll();
    void removeAllProduct(int id);
}
