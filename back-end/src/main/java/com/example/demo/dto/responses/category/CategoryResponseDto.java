package com.example.demo.dto.responses.category;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CategoryResponseDto {
    private Long id;

    private String name;

    private String description;
}
