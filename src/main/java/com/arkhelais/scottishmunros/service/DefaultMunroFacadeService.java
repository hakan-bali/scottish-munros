package com.arkhelais.scottishmunros.service;

import com.arkhelais.scottishmunros.dto.MunroListResponse;
import com.arkhelais.scottishmunros.model.Munro;
import com.arkhelais.scottishmunros.repository.Repository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultMunroFacadeService implements MunroFacadeService {

  @Autowired
  private Repository munroRepository;

  public MunroListResponse getMunros(String category, Integer minHeight, Integer maxHeight, Integer limit, String sortBy) {
    List<Munro> results = munroRepository
        .findByAllParameters(category, minHeight, maxHeight, limit);

    return MunroListResponse.builder().results(results).build();
  }

}