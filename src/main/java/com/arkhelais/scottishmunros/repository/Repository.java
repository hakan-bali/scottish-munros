package com.arkhelais.scottishmunros.repository;

import com.arkhelais.scottishmunros.dto.MunroListResponse;

public interface Repository {

  MunroListResponse findByAllParameters(String category, Double minHeight, Double maxHeight,
      Integer limit, String sort);

}
