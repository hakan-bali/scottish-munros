package com.arkhelais.scottishmunros.controller;

import static com.arkhelais.scottishmunros.dto.CategoryType.EITHER;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.arkhelais.scottishmunros.dto.MunroListResponse;
import com.arkhelais.scottishmunros.service.MunroFacadeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest   // (MunroController.class)
class MunroControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private MunroFacadeService munroFacadeService;

  @Test
  @DisplayName("Test whether required URI is available with default parameters applied")
  void testGetMappingPathAvailableWithDefaultParameters() throws Exception {
    MunroListResponse munroListResponse = MunroListResponse.builder().build();
    String jsonResponse = new ObjectMapper().writeValueAsString(munroListResponse);
    when(munroFacadeService  //eq(EITHER.name())
        .getMunros(eq(EITHER.name()), any(), any(), any(), any()))
        .thenReturn(munroListResponse);

    MvcResult mvcResult = mockMvc
        .perform(
            get("/v1/munros")
            .accept(MediaType.APPLICATION_JSON))
        //.andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(jsonResponse))
        .andReturn();

    verify(munroFacadeService).getMunros(any(), any(), any(), any(), any());
  }

  @Test
  @DisplayName("Test whether required URI is available")
  void testGetMappingPathAvailable2() throws Exception {
    MunroListResponse munroListResponse = MunroListResponse.builder().build();
    String jsonResponse = new ObjectMapper().writeValueAsString(munroListResponse);
    when(munroFacadeService
        .getMunros(any(), any(), any(), any(), any()))
        .thenReturn(munroListResponse);

    MvcResult mvcResult = mockMvc
        .perform(
            get("/v1/munros")
                .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(jsonResponse))
        .andReturn();
    System.out.println("mvcResult.getResponse() = " + mvcResult.getResponse().getContentLength());
    System.out.println("mvcResult.getResponse() = " + mvcResult.getResponse().getContentType());
    System.out.println("mvcResult.getResponse() = " + mvcResult.getResponse().getErrorMessage());

    verify(munroFacadeService).getMunros(any(), any(), any(), any(), any());
  }

}