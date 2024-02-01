package com.finaleecom.finaleecom.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "amount")
public class Amount {
    @Id
    private String amountId;
    private String email;
    private int totalAmount;

}
