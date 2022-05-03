package com.example.demo.services.implementations.product;

import com.example.demo.dto.responses.product.ProductResponseDto;
import com.example.demo.entities.ProductEntity;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.repositories.GenderRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.SportRepository;
import com.example.demo.services.interfaces.product.ProductService;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public ProductResponseDto findById(long id) {
        Optional<ProductEntity> productEntity = productRepository.findById(id);

        if (!productEntity.isPresent()) {
            throw new ProductNotFoundException();
        }

        return modelMapper.map(productEntity.orElse(null), ProductResponseDto.class);
    }

    @Override
    public List<ProductResponseDto> findAllWithFilterAndSort(List<Long> categoryIds, Long genderId, Long sportId, List<Long> technologyIds, String name, int page, int size, String sortType, String sortBy) {
        Pageable pageable = null;
        if (sortType != null && sortBy != null && !sortType.isEmpty() && !sortBy.isEmpty()) {
            pageable = PageRequest.of(page, size, Sort.by(sortType.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy));
        } else {
            pageable = PageRequest.of(page, size);
        }

        List<ProductResponseDto> result = null;
        List<String> categoryIdsInString = categoryIds != null ? categoryIds.stream().map(id -> id.toString()).collect(Collectors.toList()) : new ArrayList<>();
        List<String> technologyIdsInString = technologyIds != null ? technologyIds.stream().map(id -> id.toString()).collect(Collectors.toList()) : new ArrayList<>();

        List<ProductEntity> productEntities = productRepository.findAllFilter(
                categoryIdsInString,
                genderId,
                sportId,
                technologyIdsInString,
                name != null ? name : "",
                pageable
        );
        result = productEntities.stream().map(productEntity -> {
            return modelMapper.map(productEntity, ProductResponseDto.class);
        }).collect(Collectors.toList());

        return result;
    }
}
