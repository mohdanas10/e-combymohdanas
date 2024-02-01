package com.finaleecom.finaleecom.Model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "user")
public class User {

    @Id
    private String id ;
    private String name;
    private String userName;
    private String userPassword;
}
