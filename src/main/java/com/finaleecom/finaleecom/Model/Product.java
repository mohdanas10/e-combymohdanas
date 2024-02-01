package com.finaleecom.finaleecom.Model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "ecomproduct")
public class Product{

    @Id
    private String productId;
    private String name ;
    private String type ;
    private int price ;
    private String email;
}