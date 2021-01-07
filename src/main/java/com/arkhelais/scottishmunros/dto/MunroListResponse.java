package com.arkhelais.scottishmunros.dto;

import com.arkhelais.scottishmunros.model.Munro;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MunroListResponse {

  private List<Munro> results;

}
