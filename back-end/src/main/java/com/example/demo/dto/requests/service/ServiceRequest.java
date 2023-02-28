package com.example.demo.dto.requests.service;

import com.example.demo.dto.Dto;
import com.example.demo.dto.contextholder.UserContextHolder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

@Getter
public abstract class ServiceRequest implements Dto {

    @JsonIgnore
    protected final UserContextHolder userContextHolder;

    protected ServiceRequest(UserContextHolder userContextHolder) {
        this.userContextHolder = userContextHolder;
    }
}
