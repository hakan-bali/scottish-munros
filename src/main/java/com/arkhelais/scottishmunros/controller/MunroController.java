package com.arkhelais.scottishmunros.controller;

import com.arkhelais.scottishmunros.dto.MunroListResponse;
import com.arkhelais.scottishmunros.service.MunroFacadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/v1")
@RequiredArgsConstructor
public class MunroController {

  @Autowired
  private final MunroFacadeService munroFacadeService;

  @GetMapping(value = "/munros", produces = "application/json")
  public ResponseEntity<MunroListResponse> getCustomerList(
      @RequestParam(required = false, defaultValue = "Either") String category,
      @RequestParam(required = false) Integer minHeight,
      @RequestParam(required = false) Integer maxHeight,
      @RequestParam(required = false, defaultValue = "10") Integer limit,
      @RequestParam(required = false, defaultValue = "+height") String sortBy) {

    final MunroListResponse munroListResponse =
        munroFacadeService.getMunros(category, minHeight, maxHeight, limit, sortBy);

    return ResponseEntity.ok(munroListResponse);
  }

}
