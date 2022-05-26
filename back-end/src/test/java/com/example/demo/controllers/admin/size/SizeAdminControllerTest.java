package com.example.demo.controllers.admin.size;

import com.example.demo.configurations.SpringSecurityAuthTestConfig;
import com.example.demo.dto.requests.size.SizeRequestDto;
import com.example.demo.dto.responses.ResponseBodyDto;
import com.example.demo.dto.responses.size.SizeResponseDto;
import com.example.demo.entities.factories.responsebodydto.ResponseBodyDtoFactory;
import com.example.demo.services.interfaces.size.SizeCrudService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = SpringSecurityAuthTestConfig.class)
@AutoConfigureMockMvc(addFilters = false)
public class SizeAdminControllerTest {
  @MockBean private SizeCrudService sizeCrudService;

  @MockBean private ResponseBodyDtoFactory responseBodyDtoFactory;

  @Autowired MockMvc mockMvc;

  @Autowired private ObjectMapper mapper;

  @Captor private ArgumentCaptor<SizeRequestDto> sizeRequestDtoArgumentCaptor;

  @Captor private ArgumentCaptor<SizeResponseDto> sizeResponseDtoArgumentCaptor;

  @Test
  @WithUserDetails("admin")
  public void createSize_ShouldReturnCreatedSizeResponseDto_WhenDataValid() throws Exception {
    SizeRequestDto sizeRequestDto =
        SizeRequestDto.builder().name("name").description("description").build();
    SizeResponseDto sizeResponseDto =
        SizeResponseDto.builder().id(1L).name("name").description("description").build();
    ResponseBodyDto<SizeResponseDto> expectedResult = new ResponseBodyDto<>("200", sizeResponseDto);

    when(sizeCrudService.createSize(any())).thenReturn(sizeResponseDto);
    when(responseBodyDtoFactory.buildResponseBody(isA(SizeResponseDto.class), eq("200")))
        .thenReturn(expectedResult);

    mockMvc
        .perform(
            post("/api/admin/size")
                .content(mapper.writeValueAsString(sizeRequestDto))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andDo(print())
        .andExpect(status().isOk());

    verify(sizeCrudService).createSize(sizeRequestDtoArgumentCaptor.capture());
    SizeRequestDto passedInSizeRequestDto = sizeRequestDtoArgumentCaptor.getValue();
    assertThat(passedInSizeRequestDto.getName(), is(sizeRequestDto.getName()));
    assertThat(passedInSizeRequestDto.getDescription(), is(sizeRequestDto.getDescription()));

    verify(responseBodyDtoFactory)
        .buildResponseBody(sizeResponseDtoArgumentCaptor.capture(), eq("200"));
    SizeResponseDto passedInSizeResponseDto = sizeResponseDtoArgumentCaptor.getValue();
    assertThat(passedInSizeResponseDto.getId(), is(sizeResponseDto.getId()));
    assertThat(passedInSizeResponseDto.getName(), is(sizeResponseDto.getName()));
    assertThat(passedInSizeResponseDto.getDescription(), is(sizeResponseDto.getDescription()));
  }
}
