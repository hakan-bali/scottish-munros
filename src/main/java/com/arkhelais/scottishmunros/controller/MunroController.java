package com.arkhelais.scottishmunros.controller;

import com.arkhelais.scottishmunros.dto.MunroListResponse;
import com.arkhelais.scottishmunros.service.MunroFacadeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1")
public class MunroController {

  private final MunroFacadeService munroFacadeService;

  public MunroController(MunroFacadeService munroFacadeService) {
    this.munroFacadeService = munroFacadeService;
  }

  @GetMapping(value = "/munros", produces = "application/json")
  public ResponseEntity<MunroListResponse> getCustomerList(
      @RequestParam(required = false, defaultValue = "Either") String category,
      @RequestParam(required = false) String minHeight,
      @RequestParam(required = false) String maxHeight,
      @RequestParam(required = false, defaultValue = "10") String limit,
      @RequestParam(required = false, defaultValue = "HN") String sort) {

    return ResponseEntity.ok(
        munroFacadeService.getMunros(category, minHeight, maxHeight, limit, sort));
  }

}