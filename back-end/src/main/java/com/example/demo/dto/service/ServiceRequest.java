package com.example.demo.dto.service;

import com.example.demo.dto.Dto;
import com.example.demo.dto.contextholder.UserContextHolder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

@Getter
public abstract class ServiceRequest implements Dto {
    @JsonIgnore
    protected final UserContextHolder userContextHolder;

    protected final String resource;

    protected ServiceRequest(UserContextHolder userContextHolder,
                             String resource) {
        this.userContextHolder = userContextHolder;
        this.resource = resource;
    }
}
