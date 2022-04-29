package com.example.demo.services.implementations;

import com.example.demo.dto.responses.ProductResponseDTO;
import com.example.demo.entities.ProductEntity;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.repositories.GenderRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.SportRepository;
import com.example.demo.services.interfaces.ProductService;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ModelMapper modelMapper;

    private ProductRepository productRepository;

    private GenderRepository genderRepository;

    private SportRepository sportRepository;

    @Autowired
    public ProductServiceImpl(ModelMapper modelMapper, SportRepository sportRepository, ProductRepository productRepository) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
        this.sportRepository = sportRepository;
    }

    @Override
    public ProductResponseDTO findById(long id) {
        Optional<ProductEntity> productEntity = productRepository.findById(id);

        if (!productEntity.isPresent()) {
            throw new ProductNotFoundException();
        }

        return modelMapper.map(productEntity.orElse(null), ProductResponseDTO.class);
    }
}
