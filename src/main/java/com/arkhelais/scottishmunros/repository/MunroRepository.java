package com.arkhelais.scottishmunros.repository;

import static com.arkhelais.scottishmunros.dto.CategoryType.EITHER;
import static com.arkhelais.scottishmunros.dto.CategoryType.MUN;
import static com.arkhelais.scottishmunros.dto.CategoryType.TOP;
import static com.arkhelais.scottishmunros.exception.ErrorType.SORT_PARAMETER_INVALID;

import com.arkhelais.scottishmunros.dto.MunroListResponse;
import com.arkhelais.scottishmunros.model.Munro;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

@Slf4j
@Service
public class MunroRepository implements Repository {

  private List<Munro> munroList = new ArrayList<>();

  public MunroRepository() {
    initializeMunroRepository();
  }

  private void initializeMunroRepository() {
    try {
      File csvFile = ResourceUtils.getFile("classpath:munrotab_v6.2.csv");

      CsvToBeanBuilder<Munro> csvToBeanBuilder = new CsvToBeanBuilder<>(new FileReader(csvFile));
      munroList = csvToBeanBuilder
          .withType(Munro.class)
          .build()
          .parse();
    } catch (FileNotFoundException e) {
      log.error(e.getLocalizedMessage());
    }
  }

  @Override
  public MunroListResponse findByAllParameters(String category, Double minHeight, Double maxHeight,
      Integer limit, String sortBy) {
    List<Munro> munroFilteredSortedList =
        getMaxHeightFiltered(maxHeight,
            getMinHeightFiltered(minHeight,
                getCategoryFiltered(category, munroList.stream())))
            .sorted(getComparator(sortBy))
            .collect(Collectors.toList());
    List<Munro> munroLimitedList =
        munroFilteredSortedList.stream()
            .limit(limit)
            .collect(Collectors.toList());
    return MunroListResponse.builder()
        .total(munroList.size())
        .filtered(munroFilteredSortedList.size())
        .limit(limit)
        .results(munroLimitedList)
        .build();
  }

  private Comparator<Munro> getComparator(String sortKey) {
    Comparator<Munro> comparator = null;
    Comparator<Munro> finalComparator = null;

    for (byte sortBy : sortKey.getBytes()) {
      switch (sortBy) {
        case 'H': comparator = Comparator.comparingDouble(Munro::getHeight); break;
        case 'h': comparator = Comparator.comparingDouble(Munro::getHeight).reversed(); break;
        case 'N': comparator = Comparator.comparing(Munro::getName); break;
        case 'n': comparator = Comparator.comparing(Munro::getName).reversed(); break;
        default: throw SORT_PARAMETER_INVALID.createException();
      }
      if (finalComparator == null) {
        finalComparator = comparator;
      } else {
        finalComparator = finalComparator.thenComparing(comparator);
      }
    }
    return finalComparator;
  }

  private Stream<Munro> getMaxHeightFiltered(Double maxHeight, Stream<Munro> munroStream) {
    if (maxHeight == null) {
      return munroStream;
    } else {
      return munroStream.filter(m -> m.getHeight() < maxHeight);
    }
  }

  private Stream<Munro> getMinHeightFiltered(Double minHeight, Stream<Munro> munroStream) {
    if (minHeight == null) {
      return munroStream;
    } else {
      return munroStream.filter(m -> m.getHeight() > minHeight);
    }
  }

  private Stream<Munro> getCategoryFiltered(String category, Stream<Munro> munroStream) {
    if (EITHER.name().equalsIgnoreCase(category)) {
      munroStream = munroStream
          .filter(m -> m.getCategory().equalsIgnoreCase(MUN.name())
              || m.getCategory().equalsIgnoreCase(TOP.name()));
    } else if (MUN.name().equalsIgnoreCase(category)) {
      munroStream = munroStream
          .filter(m -> m.getCategory().equals(MUN.name()));
    } else if (TOP.name().equalsIgnoreCase(category)) {
      munroStream = munroStream
          .filter(m -> m.getCategory().equals(TOP.name()));
    }
    return munroStream;
  }

}