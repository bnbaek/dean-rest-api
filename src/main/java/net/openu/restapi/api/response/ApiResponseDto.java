package net.openu.restapi.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.openu.restapi.api.exception.ApiException;

/**
 * Created by iopenu@gmail.com on 2020/04/11
 * Github : https://github.com/bnbaek
 */
@ToString
@NoArgsConstructor
@Getter
public class ApiResponseDto<T> {

  private ApiResponseCode status;
  private String message;
  private Long timestamp;
  @JsonInclude(Include.NON_NULL)
  private T data;

  private ApiResponseDto(ApiResponseCode status) {
    this.bindStatus(status);
  }

  private ApiResponseDto(ApiResponseCode status, T data) {
    this.bindStatus(status);
    this.data = data;
    this.timestamp = Timestamp.valueOf(LocalDateTime.now()).toInstant().getEpochSecond();
  }

  private ApiResponseDto(ApiResponseCode status, String message, T data) {
    this.status = status;
    this.message = message;
    this.timestamp = Timestamp.valueOf(LocalDateTime.now()).toInstant().getEpochSecond();
    this.data = data;
  }

  private ApiResponseDto(ApiResponseCode status, ApiException e) {
    this.status = status;
    this.message = e.getMessage();
    this.timestamp = Timestamp.valueOf(LocalDateTime.now()).toInstant().getEpochSecond();
  }

  private void bindStatus(ApiResponseCode status) {
    this.status = status;
    this.message = status.getMessage();
    this.timestamp = Timestamp.valueOf(LocalDateTime.now()).toInstant().getEpochSecond();
  }

  public static <T> ApiResponseDto<T> createOK(T data) {
    return new ApiResponseDto<>(ApiResponseCode.OK, data);
  }

  public static ApiResponseDto<String> createException(ApiException e) {
    return new ApiResponseDto<>(e.getStatus(), e);
  }

  public static ApiResponseDto<String> createException(ApiResponseCode code, String message) {
    return new ApiResponseDto<>(code, message,null);
  }

  public static <T> ApiResponseDto<T> createException(ApiResponseCode code, T data) {
    return new ApiResponseDto<>(code, data);
  }

}
