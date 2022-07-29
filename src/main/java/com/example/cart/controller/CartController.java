package com.example.cart.controller;

import com.example.cart.document.Cart;
import com.example.cart.document.Product;
import com.example.cart.dto.ProductDto;
import com.example.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin(value = "*")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/get-user/{id}")
    public Cart getUserId(@PathVariable("id") int id){
        return cartService.findById(id);
    }

    @PostMapping("/add")
    public Cart addNew(@RequestBody Cart cart){
        return cartService.save(cart);
    }

    @PostMapping("/add-to-cart")
    public void addToCart(@RequestBody ProductDto productDto){
        cartService.findByIdAndAdd(productDto.getId(),productDto.getProducts());
    }

    @PostMapping("/update-cart")
    public void updateCart(@RequestBody ProductDto productDto){
        cartService.findByIdAndUpdate(productDto.getId(),productDto.getProducts());
    }

    @PostMapping("/remove-from-cart")
    public void removeFromCart(@RequestBody){

    }


}
