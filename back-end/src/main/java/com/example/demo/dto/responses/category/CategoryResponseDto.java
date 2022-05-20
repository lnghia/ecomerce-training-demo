package com.example.demo.dto.responses.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CategoryResponseDto {
    private Long id;

    private String name;

    private String description;
}
