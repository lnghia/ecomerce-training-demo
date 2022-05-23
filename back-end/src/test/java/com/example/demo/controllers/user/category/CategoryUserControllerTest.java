package com.example.demo.controllers.user.category;

import com.example.demo.dto.responses.ResponseBodyDto;
import com.example.demo.dto.responses.category.CategoryResponseDto;
import com.example.demo.entities.factories.responsebodydto.ResponseBodyDtoFactory;
import com.example.demo.services.interfaces.category.CategoryCrudService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CategoryUserControllerTest {
  @MockBean private CategoryCrudService categoryCrudService;

  @MockBean private ResponseBodyDtoFactory responseBodyDtoFactory;

  @Autowired private MockMvc mockMvc;

  @Test
  public void shouldReturnListOfCategories() throws Exception {
    List<CategoryResponseDto> categoryResponseDtoList = mock(List.class);
    ResponseBodyDto<List<CategoryResponseDto>> expectedResult =
        new ResponseBodyDto<>("200", List.of());

    when(categoryCrudService.getAll()).thenReturn(categoryResponseDtoList);
    when(responseBodyDtoFactory.buildResponseBody(categoryResponseDtoList))
        .thenReturn(expectedResult);

    mockMvc
        .perform(get("/api/category"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data", equalTo(List.of())))
        .andExpect(jsonPath("$.errors", equalTo(null)));
  }
}
