package com.example.demo.configurations.modelmapper;

import com.example.demo.dto.responses.product.ProductSizeResponseDto;
import com.example.demo.entities.ProductSizeEntity;
import org.modelmapper.*;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class ModelMapperConfigurer {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.getConfiguration().setPreferNestedProperties(true);

        resolveCircularReferencedBetweenProductAndProductSizeWhenMapping(modelMapper);

        modelMapper.addConverter(timeConverter);

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

    public static Converter<LocalDateTime, String> timeConverter = new AbstractConverter<>() {
        @Override
        protected String convert(LocalDateTime localDateTime) {
            return null == localDateTime ? null : String.valueOf(localDateTime);
        }
    };
}
