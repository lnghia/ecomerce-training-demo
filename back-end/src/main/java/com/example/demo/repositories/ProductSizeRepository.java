package com.example.demo.repositories;

import com.example.demo.entities.ProductSizeEntity;
import com.example.demo.entities.idclasses.ProductSizeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSizeRepository extends JpaRepository<ProductSizeEntity, ProductSizeId> {}
