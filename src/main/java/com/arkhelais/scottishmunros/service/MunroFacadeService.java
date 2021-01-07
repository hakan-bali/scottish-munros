package com.arkhelais.scottishmunros.service;

import com.arkhelais.scottishmunros.dto.MunroListResponse;

public interface MunroFacadeService {

  MunroListResponse getMunros(String category, Integer minHeight, Integer maxHeight, Integer limit, String sortBy);

}
