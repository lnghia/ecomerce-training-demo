package com.example.demo.command;

import com.example.demo.dto.service.ServiceRequest;
import com.example.demo.exceptions.ServerServiceException;

import java.io.IOException;

public interface Command<O, I extends ServiceRequest> {
    O execute(I request) throws IOException, ServerServiceException;
}
