package com.example.demo.command;

import com.example.demo.dto.Dto;
import com.example.demo.dto.responses.ResponseBodyDto;
import com.example.demo.dto.service.ServiceRequest;
import com.example.demo.exceptions.ServerServiceException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.validation.Validator;
import java.io.IOException;

public abstract class AbstractCommand<D extends Dto, O extends ResponseBodyDto<?>, I extends ServiceRequest>
        implements Command<O, I>, ApplicationContextAware, InitializingBean {
    protected Validator validator;

    protected ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() {
        this.validator = applicationContext.getBean(Validator.class);
    }

    protected abstract O doExecute(I request) throws IOException, ServerServiceException;

    @Override
    public O execute(I request) throws IOException, ServerServiceException {
        this.validate(request);

        return doExecute(request);
    }

    protected abstract void validate(I request) throws ServerServiceException;

//    protected void validatePayload(Object payload) throws ValidationException;
}
