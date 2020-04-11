package net.openu.restapi.api.exception;

import lombok.Getter;
import net.openu.restapi.api.response.ApiResponseCode;

/**
 * Created by iopenu@gmail.com on 2020/04/11
 * Github : https://github.com/bnbaek
 */

@Getter
public class ApiException extends RuntimeException {

  private ApiResponseCode status;
  private String message;

  /**
   * constructor
   */
  public ApiException(ApiResponseCode status, Exception e) {
    super(e);
    this.status = status;
    this.message = status.getMessage();
  }

  /**
   * constructor
   */
  public ApiException(ApiResponseCode status, String message, Exception e) {
    super(e);
    this.status = status;
    this.message = message;
  }

  /**
   * constructor
   */
  public ApiException(ApiResponseCode status) {
    this.status = status;
    this.message = status.getMessage();
  }

  /**
   * constructor
   */
  public ApiException(ApiResponseCode status, String message) {
    this.status = status;
    this.message = message;
  }
}