package net.openu.restapi.api.exception;

import lombok.Getter;

/**
 * Created by iopenu@gmail.com on 2020/04/12
 * Github : https://github.com/bnbaek
 */
@Getter
public class EmailSigninFailedException extends RuntimeException {

  private String email;
  private String password;

  public EmailSigninFailedException() {
    super("계정이 존재하지 않거나 이메일 또는 비밀번호가 정확하지 않습니다.");
  }

  public EmailSigninFailedException(String msg) {
    super(msg);
  }

  public EmailSigninFailedException(String email, String password) {
    super("계정이 존재하지 않거나 이메일 또는 비밀번호가 정확하지 않습니다.");
    this.email = email;
    this.password = password;

  }

}
