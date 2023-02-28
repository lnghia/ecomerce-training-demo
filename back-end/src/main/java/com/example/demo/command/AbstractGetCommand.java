package com.example.demo.command;

import com.example.demo.configurations.modelmapper.converters.CommonConverter;
import com.example.demo.dto.Dto;
import com.example.demo.dto.responses.ResponseBodyDto;
import com.example.demo.dto.service.GetRequest;
import com.example.demo.entities.BaseEntity;
import com.example.demo.exceptions.ServerServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGetCommand<D extends Dto, E extends BaseEntity>
        extends AbstractCommand<D, ResponseBodyDto<List<D>>, GetRequest> {
    protected JpaRepository<E, Integer> repository;

    protected final CommonConverter mapper;

    protected Class<D> responseClazz;

    protected AbstractGetCommand(
            JpaRepository<E, Integer> repository,
            CommonConverter mapper,
            Class<D> responseClazz) {
        this.repository = repository;
        this.mapper = mapper;
        this.responseClazz = responseClazz;
    }

    @Override
    protected ResponseBodyDto<List<D>> doExecute(GetRequest request) throws IOException, ServerServiceException {
        Page<E> recordsInPage = repository.findAll(request.getPageable());
        List<D> result = new ArrayList<>();

        for(E e : recordsInPage) {
            result.add(mapper.convertToResponse(e, responseClazz));
        }

        return new ResponseBodyDto<>("200", result);
    }
}
