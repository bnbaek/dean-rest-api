package net.openu.restapi.api.exception;

import lombok.Getter;

/**
 * Created by iopenu@gmail.com on 2020/04/12
 * Github : https://github.com/bnbaek
 */
@Getter
public class NotFoundException extends RuntimeException {

  private String value;

  public NotFoundException() {
    super();
  }

  public NotFoundException(String value) {
    this.value = value;

  }

}
