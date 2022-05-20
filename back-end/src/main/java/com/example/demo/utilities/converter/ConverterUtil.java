package com.example.demo.utilities.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ConverterUtil {
  @Autowired private ModelMapper modelMapper;

  public <T> Set<T> convertToSet(List<T> source) {
    return source.stream().collect(Collectors.toSet());
  }

  public <T, S> Set<S> convertListToSet(List<T> source, Class<S> destination) {
    return source.stream()
        .map(item -> modelMapper.map(item, destination))
        .collect(Collectors.toSet());
  }

  public <T, S> List<S> buildListOfType(List<T> source, Class<S> destination) {
    return source.stream()
        .map(item -> modelMapper.map(item, destination))
        .collect(Collectors.toList());
  }
}
