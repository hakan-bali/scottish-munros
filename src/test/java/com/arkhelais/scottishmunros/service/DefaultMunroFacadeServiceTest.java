package com.arkhelais.scottishmunros.service;

import static com.arkhelais.scottishmunros.dto.CategoryType.EITHER;
import static com.arkhelais.scottishmunros.dto.CategoryType.MUN;
import static com.arkhelais.scottishmunros.exception.ErrorType.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.arkhelais.scottishmunros.dto.MunroListResponse;
import com.arkhelais.scottishmunros.exception.MunroException;
import com.arkhelais.scottishmunros.repository.Repository;
import com.arkhelais.scottishmunros.util.TestBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DefaultMunroFacadeServiceTest {

  private final Repository mockRepository = mock(Repository.class);

  private DefaultMunroFacadeService munroFacadeService;

  @BeforeEach
  void setUp() {
    munroFacadeService = new DefaultMunroFacadeService(mockRepository);
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  @DisplayName("Test with the all default parameters as came in from Controller")
  public void whenAllParametersDefault_thenResultIsOk() {
    MunroListResponse response = TestBuilder.getResponse();
    when(mockRepository.findByAllParameters(any(), any(), any(), any(), any()))
        .thenReturn(response);

    MunroListResponse result = munroFacadeService
        .getMunros(EITHER.name(), null, null, "10", "HN");

    assertThat(result).isNotNull();
    assertThat(result.getTotal()).isEqualTo(response.getTotal());
  }

  @Test
  @DisplayName("Test with the invalid 'category' parameter")
  public void whenCategoryInvalid_thenExceptionThrown() {
    final Throwable throwable = assertThrows(MunroException.class,
        () -> munroFacadeService.getMunros("NO CATEGORY", null, null, "10", "HN"));

    assertThat(throwable.getLocalizedMessage())
        .contains(CATEGORY_PARAMETER_INVALID.getDescription());
  }

  @Test
  @DisplayName("Test with the invalid 'min height' parameter")
  public void whenMinHeightInvalid_thenExceptionThrown() {
    final Throwable throwable = assertThrows(MunroException.class,
        () -> munroFacadeService.getMunros(MUN.name(), "100x0", null, "10", "HN"));

    assertThat(throwable.getLocalizedMessage())
        .contains(MIN_HEIGHT_FORMAT_INVALID.getDescription());
  }

  @Test
  @DisplayName("Test with the invalid 'max height' parameter")
  public void whenMaxHeightInvalid_thenExceptionThrown() {
    final Throwable throwable = assertThrows(MunroException.class,
        () -> munroFacadeService.getMunros(MUN.name(), null, "100x0", "10", "HN"));

    assertThat(throwable.getLocalizedMessage())
        .contains(MAX_HEIGHT_FORMAT_INVALID.getDescription());
  }

  @Test
  @DisplayName("Test with 'max height' parameter lower than 'min height' parameter")
  public void whenMinMaxHeightCombinationInvalid_thenExceptionThrown() {
    final Throwable throwable = assertThrows(MunroException.class,
        () -> munroFacadeService.getMunros(MUN.name(), "1000", "900", "10", "HN"));

    assertThat(throwable.getLocalizedMessage())
        .contains(HEIGHT_ORDER_ERROR.getDescription());
  }

  @Test
  @DisplayName("Test with the invalid 'limit' parameter")
  public void whenLimitInvalid_thenExceptionThrown() {
    final Throwable throwable = assertThrows(MunroException.class,
        () -> munroFacadeService.getMunros(MUN.name(), "800", "900", "10x0", "HN"));

    assertThat(throwable.getLocalizedMessage())
        .contains(LIMIT_FORMAT_INVALID.getDescription());
  }

  @Test
  @DisplayName("Test with the inapplicable 'limit' parameter")
  public void whenLimitNotApplicable_thenExceptionThrown() {
    final Throwable throwable = assertThrows(MunroException.class,
        () -> munroFacadeService.getMunros(MUN.name(), "800", "900", "0", "HN"));

    assertThat(throwable.getLocalizedMessage())
        .contains(LIMIT_MIN_VALUE_ERROR.getDescription());
  }

  @Test
  @DisplayName("Test with the invalid 'sort' parameter")
  public void whenSortInvalid_thenExceptionThrown() {
    final Throwable throwable = assertThrows(MunroException.class,
        () -> munroFacadeService.getMunros(MUN.name(), "800", "900", "10", "HeightName"));

    assertThat(throwable.getLocalizedMessage())
        .contains(SORT_PARAMETER_INVALID.getDescription());
  }

}