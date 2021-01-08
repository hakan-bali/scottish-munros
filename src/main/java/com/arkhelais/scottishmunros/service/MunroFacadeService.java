package com.arkhelais.scottishmunros.service;

import com.arkhelais.scottishmunros.dto.MunroListResponse;

public interface MunroFacadeService {

  MunroListResponse getMunros(String category, String minHeight, String maxHeight, String limit,
      String sortBy);

}
