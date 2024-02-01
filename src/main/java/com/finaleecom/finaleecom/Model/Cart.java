package com.finaleecom.finaleecom.Model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "cart")

public class Cart {
    @Id
    private String cartId;
    private String name ;
    private String type;
    private int price ;
    private String email;
    private int totalPrice;
}
