package com.arkhelais.scottishmunros.util;

import static com.arkhelais.scottishmunros.dto.CategoryType.*;

import com.arkhelais.scottishmunros.dto.MunroListResponse;
import com.arkhelais.scottishmunros.model.Munro;
import java.util.List;

public class TestBuilder {

  public static Munro createMunro1() {
    return Munro.builder()
        .id("1")
        .category(MUN.name())
        .name("Sample Munro")
        .height(1000.0)
        .gridReference("NN111111")
        .build();
  }

  public static Munro createMunro2() {
    return Munro.builder()
        .id("2")
        .category(MUN.name())
        .name("Example Munro")
        .height(900.0)
        .gridReference("NN222222")
        .build();
  }

  public static Munro createMunroTop1() {
    return Munro.builder()
        .id("11")
        .category(TOP.name())
        .name("Sample Munro Top")
        .height(850.0)
        .gridReference("NN333333")
        .build();
  }

  public static Munro createMunroTop2() {
    return Munro.builder()
        .id("12")
        .category(TOP.name())
        .name("Example Munro Top")
        .height(950.0)
        .gridReference("NN444444")
        .build();
  }

  public static Munro createNoCategoryMunro() {
    return Munro.builder()
        .id("21")
        .category("")
        .name("Name Not Available")
        .height(999.0)
        .gridReference("NN55555")
        .build();
  }

  public static MunroListResponse getResponse() {
    return MunroListResponse.builder()
        .total(4)
        .filtered(4)
        .limit(10)
        .results(List.of(createMunro1(), createMunro2(), createMunroTop1(), createMunroTop2(),
            createNoCategoryMunro()))
        .build();
  }

}