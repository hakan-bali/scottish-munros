package com.arkhelais.scottishmunros.repository;

import com.arkhelais.scottishmunros.model.Munro;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;

@Service
public class MunroRepository implements Repository {

  private List<Munro> munroList = new ArrayList<>();

  public MunroRepository() {
    initializeMunroRepository();
  }

  private void initializeMunroRepository() {
    try {
      CsvToBeanBuilder<Munro> csvToBeanBuilder =
          new CsvToBeanBuilder<>(new FileReader("munrotab_v6.2.csv"));
      munroList = csvToBeanBuilder
          .withType(Munro.class)
          .build()
          .parse();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<Munro> findByAllParameters(String category, Integer minHeight, Integer maxHeight, Integer limit) {
    Stream<Munro> categoryFilteredStream = getCategoryFilteredStream(category, munroList.stream());
    Stream<Munro> minHeightFilteredStream = getMinHeightFiltered(minHeight, categoryFilteredStream);
    Stream<Munro> maxHeightFilteredStream = getMaxHeightFiltered(maxHeight, minHeightFilteredStream);
    return maxHeightFilteredStream.limit(limit).collect(Collectors.toList());
  }
/*
    maxHeightfiltered.sorted(
        Comparator.comparingDouble(Munro::getHeight)
            .reversed()
            .thenComparing(Munro::getName)
            .reversed())
        .limit(5)
        .forEach(System.out::println);
 */

  private Stream<Munro> getMaxHeightFiltered(Integer maxHeight, Stream<Munro> minHeightfiltered) {
    Stream<Munro> maxHeightfiltered;
    if (maxHeight == null) {
      maxHeightfiltered = minHeightfiltered;
    } else {
      maxHeightfiltered = minHeightfiltered.filter(m -> m.getHeight() < maxHeight);
    }
    return maxHeightfiltered;
  }

  private Stream<Munro> getMinHeightFiltered(Integer minHeight, Stream<Munro> categoryfiltered) {
    Stream<Munro> minHeightfiltered;
    if (minHeight == null) {
      minHeightfiltered = categoryfiltered;
    } else {
      minHeightfiltered = categoryfiltered.filter(m -> m.getHeight() > minHeight);
    }
    return minHeightfiltered;
  }

  private Stream<Munro> getCategoryFilteredStream(String category, Stream<Munro> categoryfiltered) {
    if ("Either".equalsIgnoreCase(category)) {
      categoryfiltered = categoryfiltered
          .filter(m -> m.getCategory().equals("MUN") || m.getCategory().equals("TOP"));
    } else if ("Munro".equalsIgnoreCase(category)) {
      categoryfiltered = categoryfiltered
          .filter(m -> m.getCategory().equals("MUN"));
    } else if ("MunroTop".equalsIgnoreCase(category)) {
      categoryfiltered = categoryfiltered
          .filter(m -> m.getCategory().equals("TOP"));
    }
    return categoryfiltered;
  }

}