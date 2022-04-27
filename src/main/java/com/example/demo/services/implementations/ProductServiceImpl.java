package com.example.demo.services.implementations;

import com.example.demo.dto.responses.ProductResponseDTO;
import com.example.demo.entities.ProductEntity;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.interfaces.ProductService;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@NoArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ModelMapper modelMapper;

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ModelMapper modelMapper, ProductRepository productRepository) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDTO findById(long id) {
        Optional<ProductEntity> productEntity = productRepository.findById(id);

        if (!productEntity.isPresent()) {
            throw new NoSuchElementException();
        }

        return modelMapper.map(productEntity.orElse(null), ProductResponseDTO.class);
    }
}
