package com.example.cart.service.impl;

import com.example.cart.document.Cart;
import com.example.cart.document.Product;
import com.example.cart.repository.CartRepository;
import com.example.cart.service.CartService;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Cart findById(int id) {
        return cartRepository.findById(id);
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }


    @Override
    public void findByIdAndAdd(int id, Product product) {
        Update update = new Update();
        update.addToSet("products", product);
        Criteria criteria = where("_id").is(id);
        mongoTemplate.updateFirst(Query.query(criteria), update, "cart");
        System.out.println("successfully updated!!!!");
    }

    @Override
    public void findByIdAndUpdate(int id, Product product) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id)); // find the parent
        query.addCriteria(Criteria.where("products._id").is(product.getId())); // find the child which will be changed
        Update update = new Update();
        update.set("products.$.quantity", product.getQuantity()); // change the field inside the child that must be updated

        mongoTemplate
                .findAndModify(
                        query, update,
                        new FindAndModifyOptions().returnNew(true), Cart.class
                )
        ;

    }

    //public void removeFromCart(int id){
      //  UpdateResult updateResult = mongoTemplate.updateMulti(new Query(where("_id").is(id)), new Update().pull("products", new Query(where("_id").is())), Cart.class);    }
}
