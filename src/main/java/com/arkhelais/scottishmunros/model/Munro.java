package com.arkhelais.scottishmunros.model;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Munro {

  @CsvBindByName(column = "DoBIH Number")
  private String id;

  @CsvBindByName(column = "Name")
  private String name;

  @CsvBindByName(column = "Post 1997")
  private String category;

  @CsvBindByName(column = "Height (m)", format = "###.##")
  private Double height;

  @CsvBindByName(column = "Grid Ref")
  private String gridReference;

}