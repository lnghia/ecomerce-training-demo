package com.example.demo.configurations.modelmapper.converters;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class CommonConverter {
  @Autowired private ModelMapper modelMapper;

  public <T, S> S convertToEntity(T data, Class<S> type) {
    return modelMapper.map(data, type);
  }

  public <T, S> void convertToEntity(T data, S entity) {
    modelMapper.map(data, entity);
  }

  public <T, S> S convertToResponse(T data, Class<S> type) {
    return modelMapper.map(data, type);
  }

  public <T, S> List<S> convertToResponseList(List<T> lists, Class<S> type) {
    return lists.stream().map(list -> convertToResponse(list, type)).collect(Collectors.toList());
  }
}
