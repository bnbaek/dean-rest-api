package net.openu.restapi.api.account.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.openu.restapi.account.service.AccountsDto;
import net.openu.restapi.account.service.AccountsDto.Create;
import net.openu.restapi.account.service.AccountsDto.Login;
import net.openu.restapi.account.service.AccountsDto.ResponseOne;
import net.openu.restapi.account.service.AccountService;
import net.openu.restapi.api.response.ApiResponseDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by iopenu@gmail.com on 2020/04/13
 * Github : https://github.com/bnbaek
 */

@Api(tags = {"1. Accounts"})

@RestController
@RequiredArgsConstructor
@RequestMapping(value = {"/api/v1", "/api"})
public class AccountController {

  private final AccountService accountService;

  @ApiOperation(value = "회원 가입", notes = "회원을 가입한다.")
  @PostMapping("register")
  public ApiResponseDto<ResponseOne> signUp(@RequestBody AccountsDto.Create create) {
    return ApiResponseDto.createOK(new AccountsDto.ResponseOne(accountService.signUp(create)));
  }

  @ApiOperation(value = "회원 로그인", notes = "회원을 가입한다.")
  @PostMapping("login")
  public ApiResponseDto<AccountsDto.LoginResponse> signIn(@RequestBody AccountsDto.Login login) {
    return ApiResponseDto.createOK(accountService.signIn(login));
  }

  //추후  manager
  @ApiOperation(value = "회원 승인", notes = "관리자가 회원에 상태를 업데이트한다..")
  @PutMapping("/status/{uuid}")
  public ApiResponseDto<AccountsDto.ResponseOne> approve(@ApiParam(value = "회원고유 id(uuid)", required = true) @PathVariable String uuid
      , @Valid @RequestBody AccountsDto.UpdateStatus updateStatus) {
    //TODO 이메일 형식 확인
    return ApiResponseDto.createOK(new ResponseOne(accountService.updateStatus(uuid, updateStatus)));
  }

}
