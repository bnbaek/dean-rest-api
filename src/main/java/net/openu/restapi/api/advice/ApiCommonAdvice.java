package net.openu.restapi.api.advice;

import lombok.extern.slf4j.Slf4j;
import net.openu.restapi.api.exception.AlreadyExistsException;
import net.openu.restapi.api.response.ApiResponseCode;
import net.openu.restapi.api.response.ApiResponseDto;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by iopenu@gmail.com on 2020/04/11
 * Github : https://github.com/bnbaek
 */
@Slf4j
@Order(value = 1)
@RestControllerAdvice(basePackages = "net.openu.restapi.api")
public class ApiCommonAdvice {

  /**
   * 이미 존재하는 요청 409
   */
  @ResponseStatus(HttpStatus.CONFLICT)
  @ExceptionHandler(value = {AlreadyExistsException.class})
  public ApiResponseDto<String> handleAlreadyExistsException(AlreadyExistsException e) {
    String errorMsg = String.format("[%s] %s 이(가) %s 인 데이터가 이미 존재합니다.", e.getType(), e.getFieldName(), e.getFieldValue());
    ApiResponseDto<String> exception = ApiResponseDto.createException(ApiResponseCode.ALREADY_EXIST_DATA, errorMsg);
    log.error("[{}] {}", ApiResponseCode.ALREADY_EXIST_DATA.getId(), exception);
    return exception;
  }


}