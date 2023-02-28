package com.example.demo.command;

import com.example.demo.dto.Dto;
import com.example.demo.dto.responses.ResponseBodyDto;
import com.example.demo.dto.service.ServiceRequest;

import java.util.List;

public interface GetCommand<D extends Dto, I extends ServiceRequest>
        extends AbstractCommand<D, ResponseBodyDto<List<D>>, I>{
}
