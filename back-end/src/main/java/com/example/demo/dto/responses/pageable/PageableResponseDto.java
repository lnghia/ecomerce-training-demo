package com.example.demo.dto.responses.pageable;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageableResponseDto<T> {
    private Long totalElements;

    private int totalPages;

    private int size;

    private List<T> content;
}
