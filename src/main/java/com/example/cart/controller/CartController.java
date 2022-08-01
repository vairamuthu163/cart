package com.example.cart.controller;

import com.example.cart.document.Cart;
import com.example.cart.document.Product;
import com.example.cart.dto.ProductDto;
import com.example.cart.dto.ProductsReturn;
import com.example.cart.service.CartService;
import com.mongodb.BasicDBObject;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin(value = "*")
public class CartController {

    @Autowired
    CartService cartService;



    @GetMapping("/{id}") //get all the products
    public List<Cart> getAll(@PathVariable("id") int id){
        Cart userCart = cartService.findById(id);
//        RestTemplate template = new RestTemplate();
//        String url="http://10.20.4.139:9005/products";
//        List<ProductsReturn> productsReturn= template.getForObject(url,List.class);
//        System.out.println(productsReturn);
//        return null;
            return cartService.findAll();

    }

    @GetMapping("/get-user/{id}")
    public Cart getUserId(@PathVariable("id") int id){
        System.out.println("cart response "+ id );
        return cartService.findById(id);
    }

    @GetMapping("/add-user/{id}")
    public Cart addNew(@PathVariable("id") String id){
        System.out.println(id);
        Cart userCart = cartService.findById(Integer.parseInt(id));
        Cart cartt=new Cart();
        if(userCart==null) {
            cartt.setId(Integer.parseInt(id));
            return cartService.save(cartt);
        }
        else{
            userCart.setId(Integer.parseInt(id));
            return cartService.save(cartt);
       }
    }

    @PostMapping("/add-to-cart")
    public Cart addToCart(@RequestBody ProductDto productDto){
        System.out.println("cart added!");
         cartService.findByIdAndAdd(productDto.getId(),productDto.getProducts());
         return cartService.findById(productDto.getId());
    }

    @PostMapping("/update-cart")
    public Cart updateCart(@RequestBody ProductDto productDto){
        return cartService.findByIdAndUpdate(productDto.getId(),productDto.getProducts());
    }

    @DeleteMapping("/remove-from-cart/{userId}/{id}")//for user delete
    public void removeFromCart(@PathVariable("userId") int userId, @PathVariable("id") int id){
        cartService.removeFromCart(userId,id);
    }


    //for order we have to delete the product in cart and add order db;;;
//    @DeleteMapping("/remove-cart/{userId}/{id}")//for user delete
//    public void removeAndAddToOrder(@PathVariable("userId") int userId, @PathVariable("id") int id){
//
//        RestTemplate template = new RestTemplate();
//        String url="http://10.20.4.139:9005/order/delete-product/"+id;
//        template.getForObject(url,Void.class);
//        System.out.println("deleted from cart and add to cart!!");
//
//        cartService.removeFromCart(userId,id);
//    }




    @GetMapping("/remove-cart/{id}") //for merchant delete
    public void removeFromCart(@PathVariable("id") int id){

//        List<Cart> carts = cartService.findAll();
//        for(Cart cart:carts){
//            for(Product product:cart.getProducts()){
//
//            }
//        }


        System.out.println("product deleted!!!");
        cartService.removeAllProduct(id);
    }

}
