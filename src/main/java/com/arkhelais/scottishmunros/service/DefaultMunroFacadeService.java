package com.arkhelais.scottishmunros.service;

import static com.arkhelais.scottishmunros.dto.CategoryType.EITHER;
import static com.arkhelais.scottishmunros.dto.CategoryType.MUN;
import static com.arkhelais.scottishmunros.dto.CategoryType.TOP;
import static com.arkhelais.scottishmunros.exception.ErrorType.CATEGORY_PARAMETER_INVALID;
import static com.arkhelais.scottishmunros.exception.ErrorType.HEIGHT_ORDER_ERROR;
import static com.arkhelais.scottishmunros.exception.ErrorType.LIMIT_FORMAT_INVALID;
import static com.arkhelais.scottishmunros.exception.ErrorType.LIMIT_MIN_VALUE_ERROR;
import static com.arkhelais.scottishmunros.exception.ErrorType.MAX_HEIGHT_FORMAT_INVALID;
import static com.arkhelais.scottishmunros.exception.ErrorType.MIN_HEIGHT_FORMAT_INVALID;
import static com.arkhelais.scottishmunros.exception.ErrorType.SORT_PARAMETER_INVALID;

import com.arkhelais.scottishmunros.dto.MunroListResponse;
import com.arkhelais.scottishmunros.repository.Repository;
import org.springframework.stereotype.Service;

@Service
public class DefaultMunroFacadeService implements MunroFacadeService {

  private final Repository munroRepository;

  public DefaultMunroFacadeService(Repository munroRepository) {
    this.munroRepository = munroRepository;
  }

  public MunroListResponse getMunros(String category, String minHeightText, String maxHeightText,
      String limitText, String sort) {

    checkCategoryParam(category);
    Double minHeight = getMinHeight(minHeightText);
    Double maxHeight = getMaxHeight(maxHeightText);
    checkHeightParams(minHeight, maxHeight);
    checkSortParam(sort);
    Integer limit = getLimit(limitText);

    return munroRepository.findByAllParameters(category, minHeight, maxHeight, limit, sort);
  }

  private void checkSortParam(String sort) {
    if (sort.length() < 1 || sort.length() > 2 ||
        (sort.equalsIgnoreCase("hn") && sort.equalsIgnoreCase("nh"))) {
      throw SORT_PARAMETER_INVALID.createException();
    }
  }

  private void checkCategoryParam(String category) {
    if (!category.equalsIgnoreCase(MUN.name()) &&
        !category.equalsIgnoreCase(TOP.name()) &&
        !category.equalsIgnoreCase(EITHER.name())) {
      throw CATEGORY_PARAMETER_INVALID.createException();
    }
  }

  private void checkHeightParams(Double minHeight, Double maxHeight) {
    if (minHeight != null && maxHeight != null && minHeight > maxHeight) {
      throw HEIGHT_ORDER_ERROR.createException();
    }
  }

  private Double getMinHeight(String minHeight) {
    try {
      if (minHeight == null) {
        return null;
      }
      else {
        return Double.valueOf(minHeight);
      }
    } catch (NumberFormatException exception) {
      throw MIN_HEIGHT_FORMAT_INVALID.createException();
    }
  }

  private Double getMaxHeight(String minHeight) {
    try {
      if (minHeight == null) {
        return null;
      }
      else {
        return Double.valueOf(minHeight);
      }
    } catch (NumberFormatException exception) {
      throw MAX_HEIGHT_FORMAT_INVALID.createException();
    }
  }

  private Integer getLimit(String limitString) {
    try {
      Integer limit = Integer.valueOf(limitString);
      if (limit < 1) {
        throw LIMIT_MIN_VALUE_ERROR.createException();
      }
      return limit;
    } catch (NumberFormatException exception) {
      throw LIMIT_FORMAT_INVALID.createException();
    }
  }

}