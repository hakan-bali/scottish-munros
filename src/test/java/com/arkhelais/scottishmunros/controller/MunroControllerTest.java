package com.arkhelais.scottishmunros.controller;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.arkhelais.scottishmunros.dto.MunroListResponse;
import com.arkhelais.scottishmunros.service.MunroFacadeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest(MunroController.class)
class MunroControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private MunroFacadeService munroFacadeService;

  @Test
  @DisplayName("Test whether required URI is available")
  void testGetMappingPathAvailable() throws Exception {
    when(munroFacadeService.getMunros(any(), any(), any(), any(), any()))
        .thenReturn(MunroListResponse.builder().build());

    MvcResult mvcResult = mockMvc
        .perform(get("/v1/munros").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();
    System.out.println("mvcResult.getResponse() = " + mvcResult.getResponse().getContentLength());
    System.out.println("mvcResult.getResponse() = " + mvcResult.getResponse().getContentType());
    System.out.println("mvcResult.getResponse() = " + mvcResult.getResponse().getErrorMessage());

    verify(munroFacadeService).getMunros(any(), any(), any(), any(), any());
  }

}