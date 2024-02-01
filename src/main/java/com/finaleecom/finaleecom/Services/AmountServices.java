package com.finaleecom.finaleecom.Services;


import com.finaleecom.finaleecom.Model.Amount;
import com.finaleecom.finaleecom.Repository.AmountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmountServices {
    @Autowired
    private AmountRepository amountRepository;

    public void saveAmount(Amount amount){
        this.amountRepository.save(amount);
    }

    public Amount findByEmail(String email){
        return this.amountRepository.findByEmail(email);
    }
}
