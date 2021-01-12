package com.arkhelais.scottishmunros.repository;

import static com.arkhelais.scottishmunros.dto.CategoryType.EITHER;
import static com.arkhelais.scottishmunros.dto.CategoryType.MUN;
import static com.arkhelais.scottishmunros.dto.CategoryType.TOP;

import com.arkhelais.scottishmunros.dto.MunroListResponse;
import com.arkhelais.scottishmunros.util.TestBuilder;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class MunroRepositoryTest {

  @InjectMocks
  private MunroRepository mockRepository;

  @BeforeEach
  void setUp() {
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  @DisplayName("Test with all default parameters as came in from Controller, Service")
  public void whenAllParametersDefault_thenResultIsOk() {
    MunroListResponse expectedResponse = TestBuilder.getResponse();
    ReflectionTestUtils.setField(mockRepository, "munroList", expectedResponse.getResults(), List.class);

    MunroListResponse actualResponse = mockRepository
        .findByAllParameters(EITHER.name(), null, null, 10, "HN");
    Assertions.assertThat(actualResponse.getTotal()).isEqualTo(5);
    Assertions.assertThat(actualResponse.getFiltered()).isEqualTo(4);
    Assertions.assertThat(actualResponse.getLimit()).isEqualTo(10);
  }

  @Test
  @DisplayName("Test with 'category' parameter as 'Munro' and sort as Name Descending")
  public void whenCategoryMunro_AndSortNameDescending_thenResultIsOk() {
    MunroListResponse expectedResponse = TestBuilder.getResponse();
    ReflectionTestUtils.setField(mockRepository, "munroList", expectedResponse.getResults(), List.class);

    MunroListResponse actualResponse = mockRepository
        .findByAllParameters(MUN.name(), null, null, 10, "n");
    Assertions.assertThat(actualResponse.getTotal()).isEqualTo(5);
    Assertions.assertThat(actualResponse.getFiltered()).isEqualTo(2);
    Assertions.assertThat(actualResponse.getLimit()).isEqualTo(10);
    Assertions.assertThat(actualResponse.getResults().get(0).getName()).isEqualTo("Sample Munro");
    Assertions.assertThat(actualResponse.getResults().get(1).getName()).isEqualTo("Example Munro");
  }

  @Test
  @DisplayName("Test with 'category' parameter as 'Munro' and sort as Name Ascending")
  public void whenCategoryMunro_AndSortNameAscending_thenResultIsOk() {
    MunroListResponse expectedResponse = TestBuilder.getResponse();
    ReflectionTestUtils.setField(mockRepository, "munroList", expectedResponse.getResults(), List.class);

    MunroListResponse actualResponse = mockRepository
        .findByAllParameters(MUN.name(), null, null, 10, "N");
    Assertions.assertThat(actualResponse.getTotal()).isEqualTo(5);
    Assertions.assertThat(actualResponse.getFiltered()).isEqualTo(2);
    Assertions.assertThat(actualResponse.getLimit()).isEqualTo(10);
    Assertions.assertThat(actualResponse.getResults().get(0).getName()).isEqualTo("Example Munro");
    Assertions.assertThat(actualResponse.getResults().get(1).getName()).isEqualTo("Sample Munro");
  }

  @Test
  @DisplayName("Test with 'category' parameter as 'Munro Top' and sort as Height Descending")
  public void whenCategoryMunroTop_AndSortHeightDescending_thenResultIsOk() {
    MunroListResponse expectedResponse = TestBuilder.getResponse();
    ReflectionTestUtils.setField(mockRepository, "munroList", expectedResponse.getResults(), List.class);

    MunroListResponse actualResponse = mockRepository
        .findByAllParameters(TOP.name(), null, null, 10, "h");
    Assertions.assertThat(actualResponse.getTotal()).isEqualTo(5);
    Assertions.assertThat(actualResponse.getFiltered()).isEqualTo(2);
    Assertions.assertThat(actualResponse.getLimit()).isEqualTo(10);
    Assertions.assertThat(actualResponse.getResults().get(0).getHeight()).isEqualTo(950.0);
    Assertions.assertThat(actualResponse.getResults().get(1).getHeight()).isEqualTo(850.0);
  }

  @Test
  @DisplayName("Test with 'category' parameter as 'Munro Top' and sort as Height Ascending")
  public void whenCategoryMunroTop_AndSortHeightAscending_thenResultIsOk() {
    MunroListResponse expectedResponse = TestBuilder.getResponse();
    ReflectionTestUtils.setField(mockRepository, "munroList", expectedResponse.getResults(), List.class);

    MunroListResponse actualResponse = mockRepository
        .findByAllParameters(TOP.name(), null, null, 10, "H");
    Assertions.assertThat(actualResponse.getTotal()).isEqualTo(5);
    Assertions.assertThat(actualResponse.getFiltered()).isEqualTo(2);
    Assertions.assertThat(actualResponse.getLimit()).isEqualTo(10);
    Assertions.assertThat(actualResponse.getResults().get(0).getHeight()).isEqualTo(850.0);
    Assertions.assertThat(actualResponse.getResults().get(1).getHeight()).isEqualTo(950.0);
  }

  @Test
  @DisplayName("Test with 'min height' parameter")
  public void whenMinHeight_AndSortHeightAscendingNameAscending_thenResultIsOk() {
    MunroListResponse expectedResponse = TestBuilder.getResponse();
    ReflectionTestUtils.setField(mockRepository, "munroList", expectedResponse.getResults(), List.class);

    MunroListResponse actualResponse = mockRepository
        .findByAllParameters(EITHER.name(), 875.0, null, 10, "HN");
    Assertions.assertThat(actualResponse.getTotal()).isEqualTo(5);
    Assertions.assertThat(actualResponse.getFiltered()).isEqualTo(3);
    Assertions.assertThat(actualResponse.getLimit()).isEqualTo(10);
    Assertions.assertThat(actualResponse.getResults().size()).isEqualTo(3);
    Assertions.assertThat(actualResponse.getResults().get(0).getHeight()).isEqualTo(900.0);
    Assertions.assertThat(actualResponse.getResults().get(2).getName()).isEqualTo("Sample Munro");
  }

  @Test
  @DisplayName("Test with 'max height' parameter")
  public void whenMaxHeight_AndSortHeightDescendingNameDescending_thenResultIsOk() {
    MunroListResponse expectedResponse = TestBuilder.getResponse();
    ReflectionTestUtils.setField(mockRepository, "munroList", expectedResponse.getResults(), List.class);

    MunroListResponse actualResponse = mockRepository
        .findByAllParameters(EITHER.name(), null, 1000.0, 10, "hn");
    Assertions.assertThat(actualResponse.getTotal()).isEqualTo(5);
    Assertions.assertThat(actualResponse.getFiltered()).isEqualTo(4);
    Assertions.assertThat(actualResponse.getLimit()).isEqualTo(10);
    Assertions.assertThat(actualResponse.getResults().size()).isEqualTo(4);
    Assertions.assertThat(actualResponse.getResults().get(1).getHeight()).isEqualTo(950.0);
    Assertions.assertThat(actualResponse.getResults().get(3).getName()).isEqualTo("Sample Munro Top");
  }

  @Test
  @DisplayName("Test with 'max height' parameter with limited result set'")
  public void whenMaxHeightAndiLmit_thenResultIsOk() {
    MunroListResponse expectedResponse = TestBuilder.getResponse();
    ReflectionTestUtils.setField(mockRepository, "munroList", expectedResponse.getResults(), List.class);

    MunroListResponse actualResponse = mockRepository
        .findByAllParameters(EITHER.name(), null, 2000.0, 1, "hn");
    Assertions.assertThat(actualResponse.getTotal()).isEqualTo(5);
    Assertions.assertThat(actualResponse.getFiltered()).isEqualTo(4);
    Assertions.assertThat(actualResponse.getLimit()).isEqualTo(1);
    Assertions.assertThat(actualResponse.getResults().size()).isEqualTo(1);
    Assertions.assertThat(actualResponse.getResults().get(0).getHeight()).isEqualTo(1000.0);
    Assertions.assertThat(actualResponse.getResults().get(0).getName()).isEqualTo("Sample Munro");
  }

}