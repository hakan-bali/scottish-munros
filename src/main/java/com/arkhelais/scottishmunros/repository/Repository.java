package com.arkhelais.scottishmunros.repository;

import com.arkhelais.scottishmunros.model.Munro;
import java.util.List;

public interface Repository {

  List<Munro> findByAllParameters(String category, Integer minHeight, Integer maxHeight, Integer limit);

}
