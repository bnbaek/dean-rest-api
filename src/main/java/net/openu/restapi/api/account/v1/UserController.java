package net.openu.restapi.api.account.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import net.openu.restapi.account.service.AccountsDto.ResponseOne;
import net.openu.restapi.account.service.AccountService;
import net.openu.restapi.account.service.AccountsDto;
import net.openu.restapi.api.response.ApiResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by iopenu@gmail.com on 2020/04/09
 * Github : https://github.com/bnbaek
 */

@Api(tags = {"1. Users"})

@RestController
@RequiredArgsConstructor
@RequestMapping(value = {"/api/v1/users", "/api/users"})
public class UserController {

  private final AccountService accountService;

  @ApiOperation(value = "회원 전체 조회", notes = "모든 회원을 조회한다")
  @GetMapping
  public ApiResponseDto<AccountsDto.ResponseList> accounts() {
    return ApiResponseDto.createOK(new AccountsDto.ResponseList(accountService.findAll()));
  }

  @ApiOperation(value = "회원 단건 조회", notes = "회원을 조회한다")
  @GetMapping("{email}")
  public ApiResponseDto<AccountsDto.ResponseOne> account(@ApiParam(value = "회원ID(이메일)", required = true) @PathVariable String email) {
    //TODO 이메일 형식 확인
    return ApiResponseDto.createOK(new ResponseOne(accountService.findByUsername(email)));
  }



}
