package com.example.demo.entities.factories.responsebodydto;

import com.example.demo.dto.responses.ResponseBodyDto;
import org.springframework.stereotype.Component;

@Component
public class ResponseBodyDtoFactory {
    public <T> ResponseBodyDto buildResponseBody(T source) {
        return ResponseBodyDto.builder().data(source).build();
    }
}
