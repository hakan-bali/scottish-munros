package com.arkhelais.scottishmunros.repository;

import static com.arkhelais.scottishmunros.dto.CategoryType.MUN;

import com.arkhelais.scottishmunros.dto.MunroListResponse;
import com.arkhelais.scottishmunros.model.Munro;
import com.arkhelais.scottishmunros.util.TestBuilder;
import com.opencsv.bean.CsvToBeanBuilder;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

class MunroRepositoryTest {

  @InjectMocks
  private MunroRepository mockRepository;

  @Mock
  CsvToBeanBuilder<Munro> csvToBeanBuilder;

  @BeforeEach
  void setUp() {
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void findByAllParameters() {
    MunroListResponse expectedResponse = TestBuilder.getResponse();
    ReflectionTestUtils.setField(mockRepository, "munroList", expectedResponse.getResults(), List.class);

    MunroListResponse actualResponse = mockRepository
        .findByAllParameters(MUN.name(), null, null, 10, "HN");
    Assertions.assertThat(actualResponse.getTotal()).isEqualTo(expectedResponse.getTotal());
  }
}