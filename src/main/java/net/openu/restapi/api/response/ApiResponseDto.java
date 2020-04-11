package net.openu.restapi.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModelProperty;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.openu.restapi.api.exception.ApiException;
import net.openu.restapi.api.exception.CommonSuccess;

/**
 * Created by iopenu@gmail.com on 2020/04/11
 * Github : https://github.com/bnbaek
 */
@ToString
@NoArgsConstructor
@Getter
public class ApiResponseDto<T> {

  @ApiModelProperty(value = "응답결과: OK 정상")
  private ApiResponseCode status;
  @ApiModelProperty(value = "응답 성공여부: SUCCESS(성공),FAIL(실패),ERROR(에러)")
  private CommonSuccess success;
  @ApiModelProperty(value = "응답 메시지")
  private String message;
  @ApiModelProperty(value = "응답 시간")
  private Long timestamp;
  @JsonInclude(Include.NON_NULL)
  private T data;

  private ApiResponseDto(CommonSuccess success, ApiResponseCode status) {
    this.bindStatus(success, status);
  }

  private ApiResponseDto(CommonSuccess success, ApiResponseCode status, T data) {
    this.bindStatus(success, status);
    this.data = data;
    this.timestamp = Timestamp.valueOf(LocalDateTime.now()).toInstant().getEpochSecond();
  }

  private ApiResponseDto(CommonSuccess success, ApiResponseCode status, String message, T data) {
    this.success = success;
    this.status = status;
    this.message = message;
    this.timestamp = Timestamp.valueOf(LocalDateTime.now()).toInstant().getEpochSecond();
    this.data = data;
  }

  private ApiResponseDto(CommonSuccess success, ApiResponseCode status, ApiException e) {
    this.success = success;
    this.status = status;
    this.message = e.getMessage();
    this.timestamp = Timestamp.valueOf(LocalDateTime.now()).toInstant().getEpochSecond();
  }

  private void bindStatus(CommonSuccess success, ApiResponseCode status) {
    this.success = success;
    this.status = status;
    this.message = status.getMessage();
    this.timestamp = Timestamp.valueOf(LocalDateTime.now()).toInstant().getEpochSecond();
  }

  public static <T> ApiResponseDto<T> createOK(T data) {
    return new ApiResponseDto<>(CommonSuccess.SUCCESS, ApiResponseCode.OK, data);
  }

  public static ApiResponseDto<String> createException(ApiException e) {
    return new ApiResponseDto<>(CommonSuccess.FAIL, e.getStatus(), e);
  }

  public static ApiResponseDto<String> createException(ApiResponseCode code, String message) {
    return new ApiResponseDto<>(CommonSuccess.FAIL, code, message, null);
  }

  public static <T> ApiResponseDto<T> createException(ApiResponseCode code, T data) {
    return new ApiResponseDto<>(CommonSuccess.FAIL, code, data);
  }

  public static ApiResponseDto<String> createError(ApiException e) {
    return new ApiResponseDto<>(CommonSuccess.ERROR, e.getStatus(), e);
  }

  public static ApiResponseDto<String> createError(ApiResponseCode code, String message) {
    return new ApiResponseDto<>(CommonSuccess.ERROR, code, message, null);
  }

  public static <T> ApiResponseDto<T> createError(ApiResponseCode code, T data) {
    return new ApiResponseDto<>(CommonSuccess.ERROR, code, data);
  }


}
