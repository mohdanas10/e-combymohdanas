package com.finaleecom.finaleecom.Repository;

import com.finaleecom.finaleecom.Model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, Integer> {
    public void deleteByProductId(String id );
    public Product findByProductId(String id);
    public List<Product> findByName(String name );
    public List<Product> findByType(String type);
    List<Product> findByPrice(int price );
    List<Product> findByEmail(String email);
    public Product deleteByEmail(String email);
}
