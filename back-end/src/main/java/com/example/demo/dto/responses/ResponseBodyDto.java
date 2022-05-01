package com.example.demo.dto.responses;

import lombok.Builder;

import java.util.HashMap;

@Builder(toBuilder = true)
public class ResponseBodyDto<T> {
    private String status;
    private T data;
    private HashMap<String, String> errors;

    public ResponseBodyDto() {
        status = "";
        data = null;
        errors = new HashMap<>();
    }

    public ResponseBodyDto(String status, T data, HashMap<String, String> errors) {
        this.data = data;
        this.errors = errors;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public HashMap<String, String> getErrors() {
        return errors;
    }

    public void setErrors(HashMap<String, String> errors) {
        this.errors = errors;
    }
}
