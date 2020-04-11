package net.openu.restapi.api.account.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.openu.restapi.account.service.AccountsDto.Create;
import net.openu.restapi.account.service.AccountsDto.ResponseOne;
import net.openu.restapi.account.service.AccountsService;
import net.openu.restapi.account.service.AccountsDto;
import net.openu.restapi.api.response.ApiResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by iopenu@gmail.com on 2020/04/09
 * Github : https://github.com/bnbaek
 */

@Api(tags = {"1. Account"})

@RestController
@RequiredArgsConstructor
@RequestMapping(value = {"/api/v1/accounts", "/api/accounts"})
public class AccountsController {

  private final AccountsService accountsService;

  @ApiOperation(value = "회원 가입", notes = "회원을 가입한다.")
  @PostMapping
  public ApiResponseDto<AccountsDto.ResponseOne> signUp(@RequestBody Create create) {

    return ApiResponseDto.createOK(new AccountsDto.ResponseOne(accountsService.signUp(create)));
  }

  @ApiOperation(value = "회원 전체 조회", notes = "모든 회원을 조회한다")
  @GetMapping
  public ApiResponseDto<AccountsDto.ResponseList> accounts() {
    return ApiResponseDto.createOK(new AccountsDto.ResponseList(accountsService.findAll()));
  }

  @ApiOperation(value = "회원 전체 조회", notes = "모든 회원을 조회한다")
  @GetMapping("{email}")
  public ApiResponseDto<AccountsDto.ResponseOne> account(@ApiParam(value = "회원ID(이메일)", required = true) @PathVariable String email) {
    //TODO 이메일 형식 확인
    return ApiResponseDto.createOK(new ResponseOne(accountsService.findByUsername(email)));
  }

  @ApiOperation(value = "회원 승인", notes = "관리자가 회원에 상태를 업데이트한다..")
  @PutMapping("/status/{uuid}")
  public ApiResponseDto<AccountsDto.ResponseOne> approve(@ApiParam(value = "회원고유 id(uuid)", required = true) @PathVariable String uuid
      ,@Valid @RequestBody AccountsDto.UpdateStatus updateStatus) {
    //TODO 이메일 형식 확인
    return ApiResponseDto.createOK(new ResponseOne(accountsService.updateStatus(uuid,updateStatus)));
  }


}
