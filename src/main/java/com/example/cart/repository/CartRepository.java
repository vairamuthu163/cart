package com.example.cart.repository;

import com.example.cart.document.Cart;
import com.example.cart.document.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends MongoRepository<Cart,Integer> {
    Cart findById(int id);
    Cart save(Cart cart);
}

