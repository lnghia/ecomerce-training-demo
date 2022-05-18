package com.example.demo.entities.factories.responsebodydto;

import com.example.demo.dto.responses.ResponseBodyDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ResponseBodyDtoFactory {
    public <T> ResponseBodyDto buildResponseBody(T source) {
        return ResponseBodyDto.builder().data(source).build();
    }

    public <T> ResponseBodyDto buildResponseBody(T source, String status) {
        return ResponseBodyDto.builder().status(status).data(source).build();
    }

    public <T> ResponseBodyDto buildResponseBody(T source, String status, HashMap<String, String> errors) {
        return ResponseBodyDto.builder().status(status).data(source).errors(errors).build();
    }
}
