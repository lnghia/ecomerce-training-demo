package com.example.demo.dto.service;

import com.example.demo.dto.contextholder.UserContextHolder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.data.domain.Pageable;

@Value
@EqualsAndHashCode(callSuper = true)
public class GetRequest extends ServiceRequest{
    Pageable pageable;

    public GetRequest(UserContextHolder userContextHolder, String resource, Pageable pageable) {
        super(userContextHolder, resource);
        this.pageable = pageable;
    }
}
