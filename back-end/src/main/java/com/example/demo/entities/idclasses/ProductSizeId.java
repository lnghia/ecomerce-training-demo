package com.example.demo.entities.idclasses;

import com.example.demo.entities.ProductEntity;
import com.example.demo.entities.SizeEntity;

import java.io.Serializable;

public class ProductSizeId implements Serializable {
    private ProductEntity product;
    private SizeEntity size;
}
