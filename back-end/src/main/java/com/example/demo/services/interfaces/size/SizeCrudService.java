package com.example.demo.services.interfaces.size;

import com.example.demo.dto.requests.size.SizeRequestDto;
import com.example.demo.dto.responses.size.SizeResponseDto;

public interface SizeCrudService {
    SizeResponseDto createSize(SizeRequestDto requestDto);

    SizeResponseDto updateSize(Long sizeId, SizeRequestDto requestDto);
}
