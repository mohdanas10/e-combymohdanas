package com.finaleecom.finaleecom.Repository;


import com.finaleecom.finaleecom.Model.Amount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmountRepository extends MongoRepository<Amount, String > {
   public Amount findByTotalAmount(int totalAmount);
   public Amount findByEmail(String email);
}
