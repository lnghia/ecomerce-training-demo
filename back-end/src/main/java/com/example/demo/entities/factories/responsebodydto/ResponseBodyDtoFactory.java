package com.example.demo.entities.factories.responsebodydto;

import com.example.demo.dto.responses.ResponseBodyDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ResponseBodyDtoFactory {
    public <T> ResponseBodyDto<T> buildResponseBody(T source) {
        return new ResponseBodyDto<>("200", source);
    }

    public <T> ResponseBodyDto<T> buildResponseBody(T source, String status) {
        return new ResponseBodyDto<>(status, source);
    }

    public <T> ResponseBodyDto<T> buildResponseBody(T source, String status, HashMap<String, String> errors) {
        return new ResponseBodyDto<>(status, source, errors);
    }

    public <T, S> S buildResponseBody(T source, String status, Class<S> classType) {
        return classType.cast(new ResponseBodyDto<>(status, source));
    }

    public <T, S> S buildResponseBody(T source, String status, HashMap<String, String> errors, Class<S> classType) {
        return classType.cast(new ResponseBodyDto<>(status, source, errors));
    }
}
