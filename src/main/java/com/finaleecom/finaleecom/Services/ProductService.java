package com.finaleecom.finaleecom.Services;


import com.finaleecom.finaleecom.Model.Product;
import com.finaleecom.finaleecom.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public void saveProduct(Product product){
        this.productRepository.save(product);
    }

    public List<Product> findAllProduct(){
        return this.productRepository.findAll();
    }

    public void deleteById(String id ){
        this.productRepository.deleteByProductId(id);
    }

    public Product findProductById(String id ){
        return this.productRepository.findByProductId(id);
    }

    public List<Product> searchedProductResutl(String name ){
        return this.productRepository.findByName(name);
    }

    public List<Product> getProductByType(String type){
        return this.productRepository.findByType(type);
    }

    public List<Product> getProductByPrice(int price ){
        return  this.productRepository.findByPrice(price);
    }
    public List<Product> findProductByEmail(String email){
        return this.productRepository.findByEmail(email);
    }





}
