package com.example.demo.exceptions;

import com.example.demo.dto.responses.ResponseBodyDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResponseBodyDTO response = new ResponseBodyDTO();

        response.getErrors().put("authorization", "Unauthorised");

        OutputStream out = httpServletResponse.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        httpServletResponse.setStatus(UNAUTHORIZED.value());
        httpServletResponse.setContentType(APPLICATION_JSON_VALUE);
        mapper.writeValue(out, response);
        out.flush();
    }
}
