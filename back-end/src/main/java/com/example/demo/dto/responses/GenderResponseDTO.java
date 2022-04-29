package com.example.demo.dto.responses;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class GenderResponseDTO {
    private Long id;

    private String name;
}
