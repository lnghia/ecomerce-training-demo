package com.example.demo.configurations.modelmapper;

import com.example.demo.dto.responses.ProductSizeResponseDto;
import com.example.demo.entities.ProductSizeEntity;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfigurer {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.getConfiguration().setPreferNestedProperties(true);

        resolveCircularReferencedBetweenProductAndProductSizeWhenMapping(modelMapper);

        return modelMapper;
    }

    public void resolveCircularReferencedBetweenProductAndProductSizeWhenMapping(ModelMapper modelMapper) {
        modelMapper.addMappings(new PropertyMap<ProductSizeEntity, ProductSizeResponseDto>() {
            @Override
            protected void configure() {
                skip(destination.getProduct());
            }
        });
    }
}
