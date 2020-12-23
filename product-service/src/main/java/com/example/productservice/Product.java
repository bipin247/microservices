package com.example.productservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    Long ProductId;
    String ProductName;
    String ProductDesc;
    Integer ProductAmount;
    boolean ProductInStock;

}
