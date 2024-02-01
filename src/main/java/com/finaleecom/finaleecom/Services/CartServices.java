package com.finaleecom.finaleecom.Services;


import com.finaleecom.finaleecom.Model.Cart;
import com.finaleecom.finaleecom.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CartServices {
    @Autowired
    private CartRepository cartRepository;

    public void saveCart(Cart cart){
        this.cartRepository.save(cart);
    }

    public List<Cart> getAllCart(String cartId){
        return this.cartRepository.findByCartId(cartId);
    }

    public List<Cart> allCart(){
        return this.cartRepository.findAll();

    }
    public void deleteCartById(String id ){
        this.cartRepository.deleteById(id);
    }
    public List<Cart> findCartByEmail(String email){
        return  this.cartRepository.findByEmail(email);
    }
}
