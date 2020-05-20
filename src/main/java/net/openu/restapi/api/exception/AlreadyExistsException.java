package net.openu.restapi.api.exception;

/**
 * Created by iopenu@gmail.com on 2020/04/11
 * Github : https://github.com/bnbaek
 */
// 이미 존재하는 리소스이기 때문에 중복 생성이 불가능한 경우

import lombok.Getter;

@Getter
public class AlreadyExistsException extends RuntimeException {

  private String type;
  private String fieldName;
  private Object fieldValue;


  /**
   * construct
   */
  public AlreadyExistsException(String message) {
    super(message);
  }

  /**
   * construct
   */
  public AlreadyExistsException(String type, String fieldName, Object fieldValue) {
    this.type = type;
    this.fieldName = fieldName;
    this.fieldValue = fieldValue;
  }


}