package net.openu.restapi.api.exception;

/**
 * Created by iopenu@gmail.com on 2020/04/12
 * Github : https://github.com/bnbaek
 */
public enum CommonSuccess {

  SUCCESS(0, "성공하였습니다.")
  ,FAIL(1, "실패하였습니다.")
  ,ERROR(2, "에러입니다.");

  int code;
  String msg;

  CommonSuccess(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }
}

