package net.openu.restapi.api.account.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.openu.restapi.account.service.AccountsDto;
import net.openu.restapi.account.service.AccountsDto.Response;
import net.openu.restapi.account.service.AccountsDto.ResponseOne;
import net.openu.restapi.account.service.AccountService;
import net.openu.restapi.account.service.KakaoService;
import net.openu.restapi.api.response.ApiResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by iopenu@gmail.com on 2020/04/13
 * Github : https://github.com/bnbaek
 */

@Api(tags = {"1. Accounts"})
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = {"/api/v1", "/api"})
public class AccountController {

  private final AccountService accountService;
  private final KakaoService kakaoService;

  @ApiOperation(value = "회원 가입", notes = "회원을 가입한다.")
  @PostMapping("register")
  public ApiResponseDto<ResponseOne> signUp(@RequestBody AccountsDto.Create create) {
    return ApiResponseDto.createOK(new AccountsDto.ResponseOne(accountService.signUp(create)));
  }

  @ApiOperation(value = "회원 로그인", notes = "회원을 로그인한다.")
  @PostMapping("login")
  public ApiResponseDto<AccountsDto.LoginResponse> signIn(@RequestBody AccountsDto.Login login) {
    return ApiResponseDto.createOK(accountService.signIn(login));
  }

  @ApiOperation(value = "카카오 로그인", notes = "카카오로 로그인을 한다.")
  @PostMapping("kakao/login")
  public ApiResponseDto<AccountsDto.LoginResponse> kakaoSignIn(@Valid @RequestBody AccountsDto.KaKaoLogin kaKaoLogin) {
    return ApiResponseDto.createOK(accountService.kakaoLogin(kaKaoLogin));
  }


  //추후  manager
  @ApiOperation(value = "회원 승인", notes = "관리자가 회원에 상태를 업데이트한다..")
  @PutMapping("/status/{uuid}")
  public ApiResponseDto<AccountsDto.ResponseOne> approve(@ApiParam(value = "회원고유 id(uuid)", required = true) @PathVariable String uuid
      , @Valid @RequestBody AccountsDto.UpdateStatus updateStatus) {
    //TODO 이메일 형식 확인
    return ApiResponseDto.createOK(new ResponseOne(accountService.updateStatus(uuid, updateStatus)));
  }


  @ApiOperation(value = "회원카카오연동", notes = "키카오연동을 한다.")
  @PutMapping("kakao/interlock/{uuid}")
  public ApiResponseDto<AccountsDto.ResponseOne> kakaoInterLock(
      @ApiParam(value = "회원고유 id(uuid)", required = true) @PathVariable String uuid
      , @RequestParam String kakaoAccessToken) {
    Response response = accountService.kakaoInterLock(uuid, kakaoAccessToken);

    return ApiResponseDto.createOK(new ResponseOne(response));
  }

  @GetMapping("/oauth/kakao")
  public String kakao(@RequestParam String code) {
    log.info("code {}", code);
    return code;
  }

}
