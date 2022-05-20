package com.example.demo.exceptions;

import com.example.demo.dto.responses.ResponseBodyDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {
  @Override
  public void handle(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      AccessDeniedException e)
      throws IOException, ServletException {
    ResponseBodyDto response = new ResponseBodyDto();

    response.getErrors().put("authorization", "Access denied");

    OutputStream out = httpServletResponse.getOutputStream();
    ObjectMapper mapper = new ObjectMapper();
    httpServletResponse.setStatus(FORBIDDEN.value());
    httpServletResponse.setContentType(APPLICATION_JSON_VALUE);
    mapper.writeValue(out, response);
    out.flush();
  }
}
