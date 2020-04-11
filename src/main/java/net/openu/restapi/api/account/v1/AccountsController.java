package net.openu.restapi.api.account.v1;

import lombok.RequiredArgsConstructor;
import net.openu.restapi.account.service.AccountsService;
import net.openu.restapi.account.service.AccountsDto;
import net.openu.restapi.api.response.ApiResponseDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by iopenu@gmail.com on 2020/04/09
 * Github : https://github.com/bnbaek
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = {"/api/v1/accounts", "/api/accounts"})
public class AccountsController {

  private final AccountsService accountsService;

  @PostMapping
  public ApiResponseDto<AccountsDto.Response> signUp(@RequestBody AccountsDto.Request request) {

    return ApiResponseDto.createOK(accountsService.signUp(request));


  }

}
