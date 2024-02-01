package com.finaleecom.finaleecom.Repository;


import com.finaleecom.finaleecom.Model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CartRepository extends MongoRepository<Cart, String > {
    public List<Cart> findByCartId(String cartId);
    public List<Cart> findByEmail(String email);
}
